package com.service.core.letter.dto.response;

import com.service.core.letter.domain.Letter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LetterResponse {
    private Long letterId;
    private Long senderId;
    private Long receiverId;
    private String senderMbti;
    private String receiverMbti;
    private String title;
    private String content;

    public static LetterResponse of(Letter letter) {
        return LetterResponse.builder()
                .letterId(letter.getId())
                .senderMbti(letter.getSenderMbti())
                .receiverMbti(letter.getReceiverMbti())
                .title(letter.getTitle())
                .content(letter.getContent())
                .build();
    }
}
