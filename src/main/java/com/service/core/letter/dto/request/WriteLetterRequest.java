package com.service.core.letter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriteLetterRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Long senderMbtiId;

    @NotBlank
    private Long receiverMbtiId;
}