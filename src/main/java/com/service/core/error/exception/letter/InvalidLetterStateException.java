package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class InvalidLetterStateException extends BusinessException {
    public InvalidLetterStateException() {
        super(ErrorMessage.INVALID_LETTER_STATE);
    }
}
