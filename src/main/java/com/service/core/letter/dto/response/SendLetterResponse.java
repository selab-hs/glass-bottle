package com.service.core.letter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SendLetterResponse {
    private String mbti;
    private String content;
    private String sendDate;
}
