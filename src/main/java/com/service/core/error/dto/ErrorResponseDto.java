package com.service.core.error.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ErrorResponseDto {
    private String message;
    private final LocalDateTime serverDateTime;

    public ErrorResponseDto(String message) {
        this.message = message;
        this.serverDateTime = LocalDateTime.now();
    }

    public static ResponseEntity<ErrorResponseDto> of(ErrorMessage message){
        return ResponseEntity
            .status(
                message.getStatus()
            )
            .body(
                new ErrorResponseDto(message.name())
            );
    }
}