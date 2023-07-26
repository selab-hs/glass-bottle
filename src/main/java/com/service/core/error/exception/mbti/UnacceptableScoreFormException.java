package com.service.core.error.exception.mbti;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class UnacceptableScoreFormException extends BusinessException {

    public UnacceptableScoreFormException(ErrorMessage message) {
        super(message);
    }

    public UnacceptableScoreFormException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public UnacceptableScoreFormException(String reason) {
        super(reason);
    }
}
