package com.service.core.error.exception.mbti;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class NotEqualRoundsSizeException extends BusinessException {

    public NotEqualRoundsSizeException(ErrorMessage message) {
        super(message);
    }

    public NotEqualRoundsSizeException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public NotEqualRoundsSizeException(String reason) {
        super(reason);
    }
}
