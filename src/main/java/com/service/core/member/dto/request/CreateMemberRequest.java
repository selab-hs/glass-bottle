package com.service.core.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberRequest {
    private String email;
    private String password;
    private String mbti;
}