package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotExistLetterException extends BusinessException {
    public NotExistLetterException() {
        super(ErrorMessage.NOT_EXIST_LETTER);
    }
}
