package com.service.core.error.exception.mbti;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class NotEqualRoundSizeException extends BusinessException {

    public NotEqualRoundSizeException(ErrorMessage message) {
        super(message);
    }

    public NotEqualRoundSizeException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public NotEqualRoundSizeException(String reason) {
        super(reason);
    }
}
