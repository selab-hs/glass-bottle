package com.service.core.mbti.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadMbtiQuizRoundResultResponse {
    private int roundId;
    private Long mbtiMetaId;
    private int result;
}
