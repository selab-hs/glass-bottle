package com.service.core.mbti.presentation;

import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.common.response.dto.ResponseMessage;
import com.service.core.mbti.application.MbtiService;
import com.service.core.mbti.dto.request.CreateMyMbtiRequest;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/v1/mbti"}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MbtiController {

    private final MbtiService mbtiService;

    @PostMapping
    public ResponseEntity<ResponseDto> createMyMbtiMetadata(@AuthMember UserInfo user, @RequestBody CreateMyMbtiRequest request){
        String mbti = mbtiService.mbtiQuizResultSave(user, request);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_MY_MBTI_SAVE,"내 mbti 생성 성공: "+mbti);
    }
}