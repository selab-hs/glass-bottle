package com.service.core.error.exception.member;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class EmailFormatMismatchException extends BusinessException {

    public EmailFormatMismatchException(ErrorMessage message) {
        super(message);
    }
}