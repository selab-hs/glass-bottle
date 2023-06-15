package com.service.core.member.domain.vo;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MbtiType {
    MBTI_ENFJ("ENFJ"),
    MBIT_ENTJ("ENTJ"),
    MBTI_ENFP("ENFP"),
    MBTI_ENTP("ENTP"),
    MBTI_ESFP("ESFP"),
    MBIT_ESFJ("ESFJ"),
    MBTI_ESTP("ESTP"),
    MBTI_ESTJ("ESTJ"),
    MBTI_INFP("INFP"),
    MBTI_INFJ("INFJ"),
    MBTI_INTP("INTP"),
    MBTI_ISTP("ISTP"),
    MBTI_ISFP("ISFP"),
    MBTI_ISFJ("ISFJ"),
    MBTI_ISTJ("ISTJ"),
    MBTI_INTJ("INTJ"),
    MBTI_NULL("NULL");

    public String mbit;

    public MbtiType value(String mbti){
        return MbtiType.valueOf(mbti.toUpperCase());
    }

    public static MbtiType of(String initMbtiType){
            return Arrays.stream(MbtiType.values())
                .filter(r -> r.getMbit().equals(initMbtiType))
                .findAny()
                .orElse(MBTI_NULL);
    }
}