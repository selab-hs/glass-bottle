package com.service.core.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ResponseDto<T> {

    private final String message;
    private final String serverDateTime;
    private final T data;

    public ResponseDto(ResponseMessage message, T data) {
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.data = data;
    }

    public static  <T> ResponseEntity<ResponseDto> toResponseEntity(ResponseMessage message, T data) {
        return ResponseEntity
            .status(message.getStatus())
            .body(new ResponseDto<>(message, data));
    }

    public static <T> ResponseEntity<T> created(T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data);
    }

    public static <T>ResponseEntity<T> ok(T data) {
        return ResponseEntity.ok(data);
    }
}
