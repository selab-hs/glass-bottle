package com.service.core.letter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WriteLetterResponse {
    private String title;
    private String content;
    private String senderMbti;
    private String targetMbti;
}