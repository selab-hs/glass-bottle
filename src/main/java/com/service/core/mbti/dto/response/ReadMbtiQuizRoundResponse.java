package com.service.core.mbti.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadMbtiQuizRoundResponse {
    private long id;
    private String round;
    private String description;
}
