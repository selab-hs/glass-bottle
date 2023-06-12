package com.service.core.letter.presentation;

import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.letter.application.LetterService;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.member.application.MemberService;
import com.service.core.member.domain.User;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/letters")
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<WriteLetterResponse> writeLetter(
            @RequestBody WriteLetterRequest request,
            @AuthMember UserInfo userInfo){
        User user = memberService.viewUser(userInfo.getId());
        WriteLetterResponse letter = letterService.writeLetter(request, user);
        return ResponseDto.created(letter);
    }
}
