package com.service.core.mbti.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.service.core.common.utis.MapperUtil;
import com.service.core.mbti.domain.MbtiQuizHistory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MbtiResultConverter implements AttributeConverter<MbtiQuizHistory.MbtiResult, String> {
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
