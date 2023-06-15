package com.service.core.letter.dto.response;

import com.service.core.letter.domain.Letter;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WriteLetterResponse {
    private String title;
    private String content;
    private Long senderMbtiId;
    private Long receiverMbtiId;
    private Long letterId;
    private boolean replyPossible;

    public static WriteLetterResponse of(Letter letter) {
        return WriteLetterResponse.builder()
                .title(letter.getTitle())
                .content(letter.getContent())
                .senderMbtiId(letter.getSenderMbtiId())
                .receiverMbtiId(letter.getReceiverMbtiId())
                .letterId(letter.getId())
                .replyPossible(letter.isReplyPossible())
                .build();
    }
}