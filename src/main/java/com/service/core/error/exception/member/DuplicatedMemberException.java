package com.service.core.error.exception.member;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class DuplicatedMemberException extends BusinessException {

    public DuplicatedMemberException(ErrorMessage message) {
        super(message);
    }
}