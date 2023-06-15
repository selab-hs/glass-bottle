package com.service.core.mbti.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMbtiQuizRoundAnswerRequest {
    private Long mbtiQuizRoundId;
    private Integer seq;
    private Integer roundAnswer;
}