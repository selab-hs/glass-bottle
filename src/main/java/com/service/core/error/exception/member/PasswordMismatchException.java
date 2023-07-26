package com.service.core.error.exception.member;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class PasswordMismatchException extends BusinessException {

    public PasswordMismatchException(ErrorMessage message) {
        super(message);
    }

    public PasswordMismatchException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public PasswordMismatchException(String reason) {
        super(reason);
    }
}
