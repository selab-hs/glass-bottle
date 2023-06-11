package com.service.core.letter.presentation;

import com.service.core.common.response.dto.ResponseDto;
import com.service.core.letter.application.LetterService;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/letter")
@RequiredArgsConstructor
public class LetterController {
    private final LetterService letterService;

    @PostMapping("/write")
    public ResponseEntity<WriteLetterResponse> writeLetter(@RequestBody WriteLetterRequest request){
        WriteLetterResponse letter = letterService.writeLetter(request);
        return ResponseDto.created(letter);
    }
}
