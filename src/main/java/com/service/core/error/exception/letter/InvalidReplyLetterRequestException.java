package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class InvalidReplyLetterRequestException extends BusinessException {
    public InvalidReplyLetterRequestException() {
        super(ErrorMessage.INVALID_REPLY_LETTER_REQUEST_ERROR);
    }
}
