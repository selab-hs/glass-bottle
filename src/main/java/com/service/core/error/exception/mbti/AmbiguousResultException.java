package com.service.core.error.exception.mbti;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class AmbiguousResultException extends BusinessException {

    public AmbiguousResultException(ErrorMessage message) {
        super(message);
    }

    public AmbiguousResultException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public AmbiguousResultException(String reason) {
        super(reason);
    }
}
