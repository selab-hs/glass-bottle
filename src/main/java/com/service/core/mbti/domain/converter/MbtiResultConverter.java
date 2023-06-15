package com.service.core.mbti.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.service.core.common.utis.MapperUtil;
import com.service.core.mbti.domain.*;
import com.service.core.mbti.domain.MbtiQuizHistory.MbtiResult;
import com.service.core.mbti.domain.vo.MbtiTest;
import com.service.core.mbti.dto.request.CreateMbtiQuizRoundAnswerRequest;
import com.service.core.mbti.dto.request.CreateQuizRequest;
import com.service.core.mbti.dto.request.CreateQuizRoundRequest;
import com.service.core.mbti.dto.response.ReadMbtiMetadataIdResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizRoundResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizzesResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MbtiResultConverter implements AttributeConverter<MbtiQuizHistory.MbtiResult, String> {
    private final MbtiTest mbtiQuiz;

    public MbtiQuizRound convertToMbtiQuizRoundEntity(CreateQuizRoundRequest request){
        return new MbtiQuizRound(request.getRound(), request.getDescription());
    }

    public ReadMbtiMetadataIdResponse convertToMbtiMetadataIdResponse(MbtiMetadata mbtiMetadata){
        return ReadMbtiMetadataIdResponse.builder()
            .id(mbtiMetadata.getId())
            .type(mbtiMetadata.getType())
            .name(mbtiMetadata.getName())
            .description(mbtiMetadata.getDescription())
            .build();
    }

    public List<MbtiQuiz> convertToMbtiQuizzes(List<CreateQuizRequest> requests){
        List<MbtiQuiz> mbtiQuizzes = new ArrayList<>();
        for(int i=0; i<requests.size();i++){
            mbtiQuizzes.add(
                MbtiQuiz.builder()
                    .roundId(requests.get(i).getRoundId())
                    .title(requests.get(i).getTitle())
                    .seq(i)
                    .content(requests.get(i).getContent())
                    .build()

            );
        }
        return mbtiQuizzes;
    }

    public List<ReadMbtiQuizzesResponse> convertToReadMbtiQuizzesResponse(List<MbtiQuiz> quizzes){
        List<ReadMbtiQuizzesResponse> readMbtiQuizzes = new ArrayList<>();
        for(int i=0; i<quizzes.size();i++){
            readMbtiQuizzes.add(
                ReadMbtiQuizzesResponse.builder()
                    .roundId(quizzes.get(i).getRoundId())
                    .seq(quizzes.get(i).getSeq())
                    .title(quizzes.get(i).getTitle())
                    .content(quizzes.get(i).getContent())
                    .build()
            );
        }
        return  readMbtiQuizzes;
    }

    public String convertToMbtiQuizResult(int[][] answer) {
        return mbtiQuiz.resultMbti(answer);
    }

    public List<ReadMbtiQuizRoundResponse> convertToMbtiQuizRound(List<MbtiQuizRound> roundQuiz){
        List<ReadMbtiQuizRoundResponse> rounds = new ArrayList<>();
        for (MbtiQuizRound mbtiQuizRound : roundQuiz) {
            rounds.add(
                ReadMbtiQuizRoundResponse.builder()
                    .id(mbtiQuizRound.getId())
                    .round(mbtiQuizRound.getRound())
                    .description(mbtiQuizRound.getDescription())
                    .build()
            );
        }
        return rounds;
    }

    public List<MbtiQuizHistory> convertToMbtiQuizHistory(List<CreateMbtiQuizRoundAnswerRequest> answers, Long userId){
        List<MbtiQuizHistory> roundAnswers = new ArrayList<>();
        for (CreateMbtiQuizRoundAnswerRequest request: answers) {
          roundAnswers.add(
            MbtiQuizHistory.builder()
                .roundId(request.getMbtiQuizRoundId())
                .result(new MbtiResult(request.getSeq(),request.getSeq()))
                .userId(userId)
                .build()
          );
        }
        return roundAnswers;
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