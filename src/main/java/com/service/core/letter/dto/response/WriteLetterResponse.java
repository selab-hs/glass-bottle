package com.service.core.letter.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WriteLetterResponse {
    private String title;
    private String content;
    private String senderMbti;
    private String receiverMbti;
    private Long letterId;
}