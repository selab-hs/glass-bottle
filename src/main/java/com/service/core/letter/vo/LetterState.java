package com.service.core.letter.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LetterState {
    ACTIVE("활성"),
    RECEIVE_WAITING("대기"),
    RECEIVE_COMPLETE("답변 완료"),
    EXPIRATION("만료");

    private final String description;
}
