package com.service.core.error;


import com.service.core.common.resttemplate.RestTemplateService;
import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.dto.ErrorResponseDto;
import com.service.core.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final RestTemplateService restTemplateService;

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
        var errorMessage = e.getErrorMessage();

        restTemplateService.postExceptionToSlack(e.getMessage());
        log.error("[ERROR] BusinessException -> {}", errorMessage.getMessage());

        return ErrorResponseDto.of(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorMessage = ErrorMessage.INVALID_REQUEST_PARAMETER;

        log.error("[ERROR] MethodArgumentNotValidException -> {}", e.getBindingResult());

        return ErrorResponseDto.of(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("[ERROR] Exception -> {}", e.getMessage());

        return ErrorResponseDto.of(ErrorMessage.INTERNAL_SERVER_ERROR);
    }
}
