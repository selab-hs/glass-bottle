package com.service.core.auth.filter;

import com.service.core.auth.domain.Authentication;
import com.service.core.auth.infrastructure.LocalContextHolder;
import com.service.core.auth.token.TokenProvider;
import com.service.core.config.util.HeaderUtil;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final TokenProvider tokenProvider;
    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain)
        throws IOException, ServletException {
        String token = HeaderUtil.getAccessToken((HttpServletRequest) request);

        if (token != null && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            LocalContextHolder.setContext(authentication);
        }
        if(token == null){
            Optional<String> noToken = null;
            request.setAttribute("noToken", noToken);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LocalContextHolder.remove();
    }
}