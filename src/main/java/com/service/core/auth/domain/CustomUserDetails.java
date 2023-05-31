package com.service.core.auth.domain;

import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.MbtiType;
import com.service.core.member.domain.vo.RoleType;
import java.util.Collection;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private RoleType roleType;
    private MbtiType mbtiType;
    private final Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return String.valueOf(getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CustomUserDetails create(User user){
        return new CustomUserDetails(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            RoleType.USER,
            user.getMbti(),
            Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getKey()))
        );
    }
}