package com.service.core.error.dto;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    /**
     * 서버 내부 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "내부 서버 오류"),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),

    /**
     * member 관련 오류입니다.
     */
    EMAIL_FORMAT_MISMATCH_ERROR(HttpStatus.BAD_REQUEST,"이메일 형식에 맞지 않습니다."),
    PASSWORD_FORMAT_MISMATCH_ERROR(HttpStatus.BAD_REQUEST,"비밀번호 형식에 맞지 않습니다."),
    NOT_AUTHORIZED_ERROR(HttpStatus.BAD_REQUEST,"해당 권한이 없습니다."),
    NOT_EQUALS_MEMBER_INFO_ERROR(HttpStatus.BAD_REQUEST,"해당 멤버 정보가 일치하지 않습니다."),
    DUPLICATED_MEMBER_INFO_ERROR(HttpStatus.BAD_REQUEST,"중복되는 회원입니다."),

    /**
     * mbti quiz 오류
     */
    NOT_EQUAL_ROUNDS_SIZE_ERROR(HttpStatus.BAD_REQUEST, "quiz의 라운드 수가 맞지 않습니다."),
    AMBIGUOUS_REULST_ERROR(HttpStatus.BAD_REQUEST, "테스트 결과가 모호합니다."),
    NOT_EQUAL_ROUND_SIZE_ERROR(HttpStatus.BAD_REQUEST, "라운드 내부의 문제 수 가 맞지 않습니다."),
    UNACCEPTABLE_SCORE_FORM_ERROR(HttpStatus.BAD_REQUEST, "잘못된 스코어 형태 입력입니다"),
    EMPTY_SEARCH_RESULT_ERROR(HttpStatus.BAD_REQUEST,"검색결과가 비어있습니다."),

    /**
     * letter 관련 오류입니다.
     */
    NOT_EXIST_LETTER(HttpStatus.BAD_REQUEST,"편지가 존재하지 않습니다."),
    NOT_EXIST_MBTI_TARGET(HttpStatus.BAD_REQUEST,"해당 MBTI를 가진 회원이 없습니다."),
    INVALID_REPLY_LETTER_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 답변 편지 요청입니다."),
    INVALID_SHARING_LETTER_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 공유 편지 요청입니다"),
    INVALID_LETTER_STATE(HttpStatus.BAD_REQUEST, "잘못된 편지 상태입니다."),
    INVALID_DELETE_REQUEST(HttpStatus.BAD_REQUEST, "해당 편지를 삭제할 수 없습니다."),
    CONVERSION_FAILED_QR(HttpStatus.INTERNAL_SERVER_ERROR, "QR 변환이 정상적으로 이루어지지 못했습니다. ")
    ;

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
