package com.service.core.auth.filter;

import org.springframework.security.core.Authentication;

public interface SetAuthenticationStrategy {
    void set(Authentication authentication);
}
