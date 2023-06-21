package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class InvalidSharingLetterRequestException extends BusinessException {
    public InvalidSharingLetterRequestException() {
        super(ErrorMessage.INVALID_SHARING_LETTER_REQUEST_ERROR);
    }
}
