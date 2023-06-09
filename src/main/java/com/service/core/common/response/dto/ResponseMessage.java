package com.service.core.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),
    CREATE_SUCCESS_MEMBER(HttpStatus.CREATED,"회원 가입 성공"),
    CREATE_SUCCESS_TOKEN(HttpStatus.CREATED,"로그인 성공"),
    SEARCH_SUCCESS_ME(HttpStatus.OK,"내 정보 조회 성공"),
    CREATE_SUCCESS_MY_MBTI_SAVE(HttpStatus.CREATED, "내 mbti 결과 저장 성공"),

    CREATE_SUCCESS_QIZE_ROUND(HttpStatus.CREATED,"새로운 문제 라운드가 등록에 성공했습니."),
    CREATE_SUCCESS_QIZE(HttpStatus.CREATED,"새로운 문제 등록이 성공했습니다."),
    READ_SUCCESS_QIZE_ROUND(HttpStatus.OK,"해당 ROUND의 문제출력에 성공했습니다."),
    SUCCESS_QIZE_ANSWER(HttpStatus.OK,"해당 ROUND의 답변에 성공했습니다.");

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
