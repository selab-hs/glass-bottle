package com.service.core.error.exception.mbti;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class EmptySearchResultException extends BusinessException {

    public EmptySearchResultException(ErrorMessage message) {
        super(message);
    }

    public EmptySearchResultException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public EmptySearchResultException(String reason) {
        super(reason);
    }
}
