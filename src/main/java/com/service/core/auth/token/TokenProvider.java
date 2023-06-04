package com.service.core.auth.token;

import com.service.core.auth.application.AuthUserService;
import com.service.core.auth.domain.Authentication;
import com.service.core.auth.domain.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    @Value("${jwt.token.tokenExpiry}")
    private long tokenValidTime;

    private static final String AUTHORITIES_KEY = "roles";
    private final AuthUserService customUserService;

    protected TokenProvider(@Lazy AuthUserService customUserService){
        this.customUserService = customUserService;
    }


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Long userId, String roles) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put(AUTHORITIES_KEY, roles);
        Date now = new Date();
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setExpiration(new Date(now.getTime() + tokenValidTime))
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetail userDetail = customUserService.loadUserByUsername(this.getUserPk(token));
        return new Authentication(userDetail, userDetail.getRoleType());
    }

    public String getUserPk(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}