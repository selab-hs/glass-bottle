package com.service.core.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS");

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
