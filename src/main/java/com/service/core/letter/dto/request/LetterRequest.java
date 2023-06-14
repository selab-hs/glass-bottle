package com.service.core.letter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LetterRequest {
    private Long letterId;
    private String title;
    private String content;
    private String senderMbti;
    private String receiverMbti;
}
