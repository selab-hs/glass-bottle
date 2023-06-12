package com.service.core.letter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriteLetterRequest {
    private Long senderId;
    private String title;
    private String content;
    private String senderMbti;
    private String targetMbti;
}