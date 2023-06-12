package com.service.core.mbti.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadMbtiQuizzesResponse {
    private Long roundId;
    private long seq;
    private String title;
    private String content;
}
