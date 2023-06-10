package com.service.core.letter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendLetterRequest {
    private Long senderId;
    private String mbti;
    private String content;
    private String sendDate;
}
