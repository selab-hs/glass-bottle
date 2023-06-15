package com.service.core.letter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriteLetterRequest {
    private String title;
    private String content;
    private Long senderMbtiId;
    private Long receiverMbtiId;
    private boolean replyPossible;
}