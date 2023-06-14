package com.service.core.error.exception.member;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class NotEqualsMemberInfoException extends BusinessException {

    public NotEqualsMemberInfoException(ErrorMessage message) {
        super(message);
    }
}
