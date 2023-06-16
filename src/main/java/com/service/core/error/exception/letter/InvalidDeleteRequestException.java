package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class InvalidDeleteRequestException extends BusinessException {
    public InvalidDeleteRequestException(ErrorMessage message) {
        super(ErrorMessage.INVALID_DELETE_REQUEST);
    }
}
