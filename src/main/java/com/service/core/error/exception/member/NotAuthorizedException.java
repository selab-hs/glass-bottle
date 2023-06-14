package com.service.core.error.exception.member;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class NotAuthorizedException extends BusinessException {


    public NotAuthorizedException(ErrorMessage message) {
        super(message);
    }
}