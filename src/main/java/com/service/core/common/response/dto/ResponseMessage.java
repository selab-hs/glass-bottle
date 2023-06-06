package com.service.core.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),
    CREATE_SUCCESS_MEMBER(HttpStatus.CREATED,"회원 가입 성공"),
    CREATE_SUCCESS_TOKEN(HttpStatus.CREATED,"로그인 성공"),
    SEARCH_SUCCESS_ME(HttpStatus.OK,"내 정보 조회 성공");

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
