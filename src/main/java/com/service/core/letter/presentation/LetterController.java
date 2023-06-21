package com.service.core.letter.presentation;

import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.common.response.dto.ResponseMessage;
import com.service.core.letter.application.LetterService;
import com.service.core.letter.dto.request.ReplyLetterRequest;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/letters")
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;

    @PostMapping
    public ResponseEntity<ResponseDto> writeLetterToAppointTargetUsers(
            @Valid
            @RequestBody WriteLetterRequest request,
            @AuthMember UserInfo userInfo) {
        letterService.writeLetterToAppointTargetUsers(request, userInfo);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_LETTER, "해당 MBTI 유저에게 편지 전송 성공");
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto> writeLetterToAllUsers(
            @Valid
            @RequestBody WriteLetterRequest request,
            @AuthMember UserInfo userInfo) {
        letterService.writeLetterToAllUsers(request, userInfo);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_LETTER, "전체 랜덤 유저에게 편지 전송 성공");
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto> replyLetter(
            @Valid
            @PathVariable("id") Long id,
            @RequestBody ReplyLetterRequest request,
            @AuthMember UserInfo userInfo) {
        letterService.replyLetter(request, userInfo, id);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_LETTER, "편지 답장 성공");
    }

    @GetMapping
    public ResponseEntity<List<WriteLetterResponse>> findAllLetters() {
        var letters = letterService.findAllLetters();
        return ResponseDto.ok(letters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WriteLetterResponse> findLetterById(@PathVariable("id") Long id) {
        WriteLetterResponse letter = letterService.findLetterById(id);
        return ResponseDto.ok(letter);
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<String> startReplyLetter(@AuthMember UserInfo userInfo, @PathVariable Long id) {
        letterService.startReplyLetter(userInfo, id);
        return ResponseDto.ok("Reply Timer : 30:00");
    }

    @GetMapping("/sharing/{id}/{receiveId}")
    public ResponseEntity<String> sharingLetter(@AuthMember UserInfo userInfo, @PathVariable Long id, @PathVariable Long receiveId) {
        var dataQR = letterService.sharingLetter(userInfo, id, receiveId);
        return ResponseDto.ok(dataQR);
    }
}
