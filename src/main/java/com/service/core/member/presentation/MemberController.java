package com.service.core.member.presentation;

import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.common.response.dto.ResponseMessage;
import com.service.core.member.application.MemberService;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<ResponseDto> getUserInfo(@AuthMember UserInfo info){
        User user = memberService.viewUser(info.getId());
        return ResponseDto.toResponseEntity(ResponseMessage.SEARCH_SUCCESS_ME, user);
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseDto> getUserInfo(){
        User admin = memberService.createAdminMember();
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_ADMIN, admin);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> createUser(@RequestBody CreateMemberRequest createMemberRequest){
        memberService.createMember(createMemberRequest);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_MEMBER,"회원가입 완료");
    }
}