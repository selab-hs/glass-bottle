package com.service.core.mbti.presentation;

import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.common.response.dto.ResponseMessage;
import com.service.core.mbti.application.MbtiService;
import com.service.core.mbti.domain.MbtiQuiz;
import com.service.core.mbti.domain.MbtiQuizRound;
import com.service.core.mbti.dto.request.CreateMyMbtiRequest;
import com.service.core.mbti.dto.request.CreateQuizRequest;
import com.service.core.mbti.dto.request.CreateMbtiQuizRoundAnswerRequest;
import com.service.core.mbti.dto.request.CreateQuizRoundRequest;
import com.service.core.mbti.dto.response.ReadMbtiQuizRoundResponse;
import com.service.core.mbti.dto.response.ReadMbtiQuizzesResponse;
import com.service.core.member.dto.response.UserInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/mbti"})
@RequiredArgsConstructor
public class MbtiController {

    private final MbtiService mbtiService;

    //mbti 결과 확인
    @PostMapping
    public ResponseEntity<ResponseDto> createMyMbtiMetadata(@AuthMember UserInfo user, @RequestBody CreateMyMbtiRequest request){
        String mbti = mbtiService.mbtiQuizResultSave(user, request);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_MY_MBTI_SAVE,"내 mbti 생성 성공: "+mbti);
    }

    //라운드 생성
    @PostMapping("/quizzes/rounds")
    public ResponseEntity<ResponseDto> createQuizRound(
        @AuthMember UserInfo user,
        @RequestBody CreateQuizRoundRequest request){
        mbtiService.createQuizRound(user, request);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_QIZE_ROUND,"새로운 Quiz Round 생성 성공");
    }

    //리스트 형식으로 퀴즈 생성
    @PostMapping("/quizzes")
    public ResponseEntity<?> createQuiz(
        @AuthMember UserInfo user,
        @RequestBody List<CreateQuizRequest> request){
        mbtiService.createQuiz(user, request);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_QIZE, "문제 생성 성공");
    }

    //해당 퀴즈의 해답 저장
    @PostMapping("/quizzes/rounds/answers")
    public ResponseEntity<ResponseDto> roundAnswers(
        @AuthMember UserInfo user,
        @RequestBody List<CreateMbtiQuizRoundAnswerRequest> answers){
        mbtiService.quizRoundAnswer(answers, user);
        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS_QIZE_ANSWER,"퀴즈 답변에 성공했습니다.");
    }

    //해당 퀴즈 라운드 종류 전부 출력
    @GetMapping("/quizzes/rounds/{roundId}")
    public ResponseEntity<ResponseDto> getSelectQuizRound(@RequestParam Long roundId){
        List<ReadMbtiQuizzesResponse>  roundQuizzes= mbtiService.getFindQuizzes(roundId);
        return ResponseDto.toResponseEntity(ResponseMessage.READ_SUCCESS_QIZE_ROUND, roundQuizzes);
    }

    //퀴즈 라운드 종류 전부 출력
    @GetMapping("/quizzes/rounds")
    public ResponseEntity<ResponseDto> getQuizRound(){
        List<ReadMbtiQuizRoundResponse> quizs = mbtiService.getQuizRound();
        return ResponseDto.toResponseEntity(ResponseMessage.READ_SUCCESS_QIZE_ROUND, quizs);
    }
}