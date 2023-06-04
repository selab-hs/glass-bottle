package com.service.core.auth.infrastructure.authenticaionStrategy;

import com.service.core.auth.domain.Authentication;
import com.service.core.auth.infrastructure.LocalContextHolder;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationStrategy implements GetAuthenticationStrategy,
    SetAuthenticationStrategy {


    public Authentication get(HttpServletRequest request) {
        return LocalContextHolder.getContext();
    }

    public void set(Authentication authentication) {
       LocalContextHolder.setContext(authentication);
    }
}