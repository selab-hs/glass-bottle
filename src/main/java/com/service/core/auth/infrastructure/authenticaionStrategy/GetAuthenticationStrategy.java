package com.service.core.auth.infrastructure.authenticaionStrategy;

import com.service.core.auth.domain.Authentication;
import javax.servlet.http.HttpServletRequest;

public interface GetAuthenticationStrategy {
    Authentication get(HttpServletRequest request);
}
