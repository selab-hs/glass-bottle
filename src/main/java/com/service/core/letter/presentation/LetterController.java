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
    public ResponseEntity<ResponseDto> writeLetter(
            @Valid
            @RequestBody WriteLetterRequest request,
            @AuthMember UserInfo userInfo) {
        letterService.writeLetter(request, userInfo);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_LETTER,"편지 작성 성공");
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto> replyLetter(
            @Valid
            @PathVariable("id") Long id,
            @RequestBody ReplyLetterRequest request,
            @AuthMember UserInfo userInfo) {
        letterService.replyLetter(request, userInfo, id);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_LETTER,"편지 답장 성공");
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
}
