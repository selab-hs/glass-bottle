package com.service.core.auth.presentation;

import com.service.core.auth.application.AuthUserService;
import com.service.core.common.response.dto.ResponseDto;
import com.service.core.common.response.dto.ResponseMessage;
import com.service.core.member.dto.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserService customUserService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody JoinRequest joinRequest) {
        String token = customUserService.userLogin(joinRequest);
        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_TOKEN, token);
    }
}