package com.service.core.mbti.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.service.core.common.utis.MapperUtil;
import com.service.core.mbti.domain.MbtiMetadata;
import com.service.core.mbti.domain.MbtiQuizHistory;
import com.service.core.mbti.domain.MemberMbti;
import com.service.core.mbti.domain.vo.MbtiQuiz;
import javax.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MbtiResultConverter implements AttributeConverter<MbtiQuizHistory.MbtiResult, String> {
    private final MbtiQuiz mbtiQuiz;

    public String toMbtiQuizResult(int[][] answer) {
        return mbtiQuiz.resultMbti(answer);
    }

    public MemberMbti convertToMemberMbti(Long userId, MbtiMetadata mbti){
        return new MemberMbti(userId, mbti.getId());
    }

    @Override
    public String convertToDatabaseColumn(MbtiQuizHistory.MbtiResult attribute) {
        try {
            return MapperUtil.mapper().writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public MbtiQuizHistory.MbtiResult convertToEntityAttribute(String dbData) {
        try {
            return MapperUtil.mapper().readValue(dbData, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}