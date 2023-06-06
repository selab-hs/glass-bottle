package com.service.core.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequest {
    private String email;
    private String password;
}