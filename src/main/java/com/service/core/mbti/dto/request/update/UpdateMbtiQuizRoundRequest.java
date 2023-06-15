package com.service.core.mbti.dto.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMbtiQuizRoundRequest {
    private String round;
    private String description;
}
