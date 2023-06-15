package com.service.core.mbti.dto.request.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizRequest {
    private Long roundId;
    private String title;
    private Long seq;
    private String content;
}