package com.service.core.auth.infrastructure.authenticaionStrategy;

import com.service.core.auth.domain.Authentication;

public interface SetAuthenticationStrategy {
    void set(Authentication authentication);
}
