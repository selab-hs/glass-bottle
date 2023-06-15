package com.service.core.letter.dto.request;

import com.service.core.letter.domain.Letter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyLetterRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long letterId;

    private Long senderMbtiId;

    private Long receiverMbtiId;

    public ReplyLetterRequest(Letter letter) {
        this.letterId = letter.getId();
    }
}
