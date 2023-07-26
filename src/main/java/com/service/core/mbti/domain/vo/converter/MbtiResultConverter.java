package com.service.core.mbti.domain.vo.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.service.core.common.utis.MapperUtil;
import com.service.core.mbti.domain.*;
import com.service.core.mbti.domain.vo.MbtiTest;
import com.service.core.mbti.dto.request.create.CreateMbtiQuizRoundAnswerRequest;
import com.service.core.mbti.dto.request.create.CreateQuizRequest;
import com.service.core.mbti.dto.request.create.CreateQuizRoundRequest;
import com.service.core.mbti.dto.response.ReadMbtiMetadataIdResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizRoundResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizRoundResultResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizzesResponse;
import com.service.core.member.dto.response.UserInfo;
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

    public ReadMbtiQuizRoundResultResponse convertToMbtiQuizRoundResultResponse(int roundId, long mbtiMetadataSum, Long top, int down){
        return ReadMbtiQuizRoundResultResponse.builder()
            .roundId(roundId)
            .mbtiMetaId(mbtiMetadataSum)
            .result((convertResultAverage(top, down)))
            .build();
    }

    public ReadMbtiQuizRoundResultResponse convertToMyMbtiQuizRoundResultResponse(List<MbtiQuizHistory> answers, Long roundId, Long userId){
        Long sum = 0L;
        for(MbtiQuizHistory answer : answers){
            sum += answer.getAnswer();
        }
       return ReadMbtiQuizRoundResultResponse.builder()
           .roundId(Math.toIntExact(roundId))
           .mbtiMetaId(userId)
           .result(convertResultAverage(sum, answers.size()))
           .build();
    }

    private int convertResultAverage(Long top, int down){
        if(top==0||down==0){
            return 0;
        }
        double sum = ((double)top/((double) down*7))*100;
        return (int) sum ;
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

    public List<MbtiQuizHistory> convertToMbtiQuizHistory(List<CreateMbtiQuizRoundAnswerRequest> answers, UserInfo user){
        List<MbtiQuizHistory> roundAnswers = new ArrayList<>();
        for (CreateMbtiQuizRoundAnswerRequest request: answers) {
          roundAnswers.add(
            MbtiQuizHistory.builder()
                .roundId(request.getMbtiQuizRoundId())
                .seg(request.getSeq())
                .answer(request.getRoundAnswer())
                .mbtiMetadataId(user.getMbtiId())
                .userId(user.getId())
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