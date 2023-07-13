package com.service.core.mbti.application;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.mbti.EmptySearchResultException;
import com.service.core.error.exception.member.NotAuthorizedException;
import com.service.core.mbti.domain.MbtiMetadata;
import com.service.core.mbti.domain.MbtiQuiz;
import com.service.core.mbti.domain.MbtiQuizHistory;
import com.service.core.mbti.domain.MbtiQuizRound;
import com.service.core.mbti.domain.vo.MbtiRoundResult;
import com.service.core.mbti.domain.vo.converter.MbtiResultConverter;
import com.service.core.mbti.dto.request.MyMbtiRequest;
import com.service.core.mbti.dto.request.create.CreateQuizRequest;
import com.service.core.mbti.dto.request.create.CreateQuizRoundRequest;
import com.service.core.mbti.dto.request.create.CreateMbtiQuizRoundAnswerRequest;
import com.service.core.mbti.dto.request.update.UpdateMbtiQuizRoundRequest;
import com.service.core.mbti.dto.request.update.UpdateQuizRequest;
import com.service.core.mbti.dto.response.ReadMbtiQuizRoundResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizRoundResultResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizzesResponse;
import com.service.core.mbti.infrastructure.MbtiMetadataRepository;
import com.service.core.mbti.infrastructure.MbtiQuizHistoryRepository;
import com.service.core.mbti.infrastructure.MbtiQuizRepository;
import com.service.core.mbti.infrastructure.MbtiQuizRoundRepository;
import com.service.core.mbti.infrastructure.MemberMbtiRepository;
import com.service.core.member.application.MemberService;
import com.service.core.member.domain.vo.RoleType;
import com.service.core.member.dto.request.UpdateMemberMbtiRequest;
import com.service.core.member.dto.response.UserInfo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MbtiService {
    private final MemberMbtiRepository memberMbtiRepository;
    private final MbtiMetadataRepository mbtiMetadataRepository;
    private final MbtiQuizRoundRepository mbtiQuizRoundRepository;
    private final MbtiQuizHistoryRepository mbtiQuizHistoryRepository;
    private final MbtiQuizRepository mbtiQuizRepository;
    private final MbtiResultConverter converter;
    private final MemberService memberService;

    @Transactional
    public String mbtiQuizResultSave(UserInfo user, MyMbtiRequest request) {
            String mbtiName = converter.convertToMbtiQuizResult(request.getMbtiRequest());
            Long mbtiId = mbtiMetadataRepository.findByType(mbtiName).get().getId();
            memberService.updateMember(user,new UpdateMemberMbtiRequest(mbtiId));
            memberMbtiRepository
                .save(
                    converter.convertToMemberMbti(
                        user.getId(),
                        mbtiMetadataRepository.findByType(mbtiName).orElseThrow(
                            ()-> new EmptySearchResultException(ErrorMessage.EMPTY_SEARCH_RESULT_ERROR)
                        )
                    )
                );
            return mbtiName;
    }

    @Transactional
    public void deleteQuizRound(UserInfo user, Long id){
        if(user.getRoleType().equals(RoleType.ADMIN.getKey())){
            mbtiQuizHistoryRepository.deleteByRoundId(id);
            mbtiQuizRoundRepository.deleteById(id);
        }
    }

    @Transactional
    public void createQuiz(UserInfo user, List<CreateQuizRequest> request){
        if(user.getRoleType().equals(RoleType.ADMIN.getKey())){
           mbtiQuizRepository.saveAll(converter.convertToMbtiQuizzes(request));
        }else{
            throw new NotAuthorizedException(ErrorMessage.NOT_AUTHORIZED_ERROR);
        }
    }

    @Transactional
    public void updateQuiz(UserInfo user, Long id,Integer seq, UpdateQuizRequest request){
        if(user.getRoleType().equals(RoleType.ADMIN.getKey())){
                MbtiQuiz quiz = mbtiQuizRepository.findById(id)
                    .orElseThrow(
                        () -> new EmptySearchResultException(ErrorMessage.EMPTY_SEARCH_RESULT_ERROR,
                            "입력한 id가 존재하지 않습니다."));
                quiz.update(
                    request.getRoundId(),
                    seq,
                    request.getTitle(),
                    request.getContent());
            mbtiQuizRepository.save(quiz);
        }
    }

    @Transactional
    public void deleteQuiz(UserInfo user, Long id, Integer seq){
        if(user.getRoleType().equals(RoleType.ADMIN.getKey())){
            mbtiQuizRepository.deleteByIdAndSeq(id, seq);
        }
    }

    //질문 round 생성
    @Transactional
    public void createQuizRound(UserInfo user, CreateQuizRoundRequest request){
        if(user.getRoleType().equals(RoleType.ADMIN.getKey())){
            mbtiQuizRoundRepository.save(converter.convertToMbtiQuizRoundEntity(request));
        }else{
            throw new NotAuthorizedException(ErrorMessage.NOT_AUTHORIZED_ERROR);
        }
    }

    // 질문 round 수정
    @Transactional
    public void updateQuizRound(UserInfo user,Long id, UpdateMbtiQuizRoundRequest request){
        if((user.getRoleType().equals(RoleType.ADMIN.getKey()))){
            MbtiQuizRound updateMbtiQuizRound = mbtiQuizRoundRepository.findById(id)
                .orElseThrow(
                    () -> new EmptySearchResultException(ErrorMessage.EMPTY_SEARCH_RESULT_ERROR,
                        "요청한 아이디의 결과값이 없습니다."));
            updateMbtiQuizRound.update(request.getRound(), request.getDescription());
            mbtiQuizRoundRepository.save(updateMbtiQuizRound);
        }
    }

    // 유저 답변 저장
    @Transactional
    public void quizRoundAnswer(List<CreateMbtiQuizRoundAnswerRequest> answer, UserInfo user){
        mbtiQuizHistoryRepository.saveAllAndFlush(converter.convertToMbtiQuizHistory(answer, user));
    }

    //해당 문제 리스트 별로 출력
    @Transactional
    public List<ReadMbtiQuizzesResponse> getFindQuizzes(Long roundId){
       return converter.convertToReadMbtiQuizzesResponse(mbtiQuizRepository.findByRoundId(roundId));
    }

    //라운드 종류 전체 조회
    @Transactional(readOnly = true)
    public List<ReadMbtiQuizRoundResponse> getQuizRound(){
        return converter.convertToMbtiQuizRound(mbtiQuizRoundRepository.findAll());
    }

    //mbti 별로 모든 답변의 확률을 %로 볼 수 있다.
    @Transactional(readOnly = true)
    public List<ReadMbtiQuizRoundResultResponse> getAllMbtiQuizRoundResult(Long roundId){
        List<MbtiMetadata> mbtis = mbtiMetadataRepository.findAll();
        MbtiRoundResult mbtisResult = new MbtiRoundResult(mbtiQuizHistoryRepository.findByRoundId(roundId), mbtis);
        List<ReadMbtiQuizRoundResultResponse> results = new ArrayList<>();

        for(Long mbtiMetadataSum : mbtisResult.getResult().keySet()){
            results.add(converter.convertToMbtiQuizRoundResultResponse(
                Math.toIntExact(roundId),
                mbtiMetadataSum,
                mbtisResult.getResult().get(mbtiMetadataSum).longValue(),
                mbtiQuizHistoryRepository.findByRoundIdAndMbtiMetadataId(roundId, mbtiMetadataSum).size()
                ));
        }

        return results;
    }

    // 자신의 quiz round 결과를 조회하는 기능
    @Transactional(readOnly = true)
    public ReadMbtiQuizRoundResultResponse getMbtiQuizMyRoundResult(UserInfo user, Long roundId){
        List<MbtiQuizHistory> answers = mbtiQuizHistoryRepository.findByUserIdAndRoundId(user.getId(), roundId);
        return converter.convertToMyMbtiQuizRoundResultResponse(answers, roundId, user.getId());
    }
}