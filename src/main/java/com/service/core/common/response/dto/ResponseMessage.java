package com.service.core.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),

    /**
     * 유저 성공 message
     */
    CREATE_SUCCESS_MEMBER(HttpStatus.CREATED,"회원 가입 성공"),
    DELETE_SUCCESS_MEMBER(HttpStatus.OK,"회원 탈퇴 성공"),
    CREATE_SUCCESS_ADMIN(HttpStatus.CREATED,"admin 회원 가입 성공"),
    CREATE_SUCCESS_TOKEN(HttpStatus.CREATED,"로그인 성공"),
    SEARCH_SUCCESS_ME(HttpStatus.OK,"내 정보 조회 성공"),
    CREATE_SUCCESS_MY_MBTI_SAVE(HttpStatus.CREATED, "내 mbti 결과 저장 성공"),

    /**
     * Mbti 성공 message
     */
    CREATE_SUCCESS_QIZE_ROUND(HttpStatus.CREATED,"새로운 문제 라운드가 등록에 성공했습니."),
    CREATE_SUCCESS_QIZE(HttpStatus.CREATED,"새로운 문제 등록이 성공했습니다."),
    UPDATE_SUCCESS_QIZE_ROUND(HttpStatus.CREATED,"문제 라운드 수정이 성공했습니다."),
    UPDATE_SUCCESS_QIZE(HttpStatus.CREATED,"문제 등록 수정이 성공했습니다."),
    READ_SUCCESS_QIZE_ROUND(HttpStatus.OK,"해당 ROUND의 문제출력에 성공했습니다."),
    SUCCESS_QIZE_ANSWER(HttpStatus.OK,"해당 ROUND의 답변에 성공했습니다."),
    READ_SUCCESS_QIZE_ROUND_RESULT(HttpStatus.OK,"해당 ROUND마다 mbti의 결과를 확인 성공했습니다."),
    SUCCESS_SEARCH_MBTI(HttpStatus.OK,"해당 id의 mbti 메타 데이터 검색에 성공했습니다."),


    CREATE_SUCCESS_LETTER(HttpStatus.CREATED,"편지 작성 성공"),
    SEND_SUCCESS_LETTER(HttpStatus.OK,"편지 전송 성공")
    ;

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
