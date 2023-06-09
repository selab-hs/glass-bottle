package com.service.core.error.dto;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    /**
     * 서버 내부 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "내부 서버 오류"),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),

    /**
     * mbti quiz 오류
     */
    NOT_EQUAL_ROUNDS_SIZE_ERROR(HttpStatus.BAD_REQUEST, "quiz의 라운드 수가 맞지 않습니다."),
    NOT_EQUAL_ROUND_SIZE_ERROR(HttpStatus.BAD_REQUEST, "라운드 내부의 문제 수 가 맞지 않습니다."),
    UNACCEPTABLE_SCORE_FORM_ERROR(HttpStatus.BAD_REQUEST, "잘못된 스코어 형태 입력입니다");

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}