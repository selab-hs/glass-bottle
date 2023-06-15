package com.service.core.letter.presentation;

import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.common.response.dto.ResponseMessage;
import com.service.core.letter.application.LetterService;
import com.service.core.letter.application.RandomSend;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.member.application.MemberService;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/letters")
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;
    private final MemberService memberService;
    private final RandomSend randomSend;

    //편지 저장
    @PostMapping
    public ResponseEntity<ResponseDto> writeLetter(
            @RequestBody WriteLetterRequest request,
            @AuthMember UserInfo userInfo) {
        letterService.writeLetter(request, userInfo);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_LETTER,"편지 작성 성공");
    }

/*    @PostMapping("/send")
    public ResponseEntity<ResponseDto> sendLetter(
            @RequestBody WriteLetterResponse response,
            @AuthMember UserInfo userInfo) {
        letterService.appointTargetMbti(response, userInfo);
        return ResponseDto.toResponseEntity(ResponseMessage.SEND_SUCCESS_LETTER,"편지 전송 성공");
    }*/

/*    @GetMapping
    public ResponseEntity<List<SendLetter>> findAllLetters() {
        var letters = letterService.findAllLetters();
        return ResponseDto.ok(letters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LetterResponse> findLetterById(@PathVariable("id") Long id) {
        LetterResponse letter = letterService.findLetterById(id);
        return ResponseDto.ok(letter);
    }*/
}
