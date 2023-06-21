package com.service.core.error.exception.letter;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.BusinessException;

public class QRConversionFailedException extends BusinessException {
    public QRConversionFailedException() {
        super(ErrorMessage.CONVERSION_FAILED_QR);
    }
}
