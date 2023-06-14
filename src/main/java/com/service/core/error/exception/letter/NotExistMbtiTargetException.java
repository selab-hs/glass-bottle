package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotExistMbtiTargetException extends BusinessException {
    public NotExistMbtiTargetException() {
        super(ErrorMessage.NOT_EXIST_MBTI_TARGET);
    }
}
