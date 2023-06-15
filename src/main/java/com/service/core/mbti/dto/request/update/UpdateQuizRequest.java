package com.service.core.mbti.dto.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuizRequest {
    private Long roundId;
    private String title;
    private String content;
}
