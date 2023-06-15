package com.service.core.letter.dto.request;

import com.service.core.letter.vo.LetterState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriteLetterRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long senderMbtiId;

    private Long receiverMbtiId;

    @Enumerated(EnumType.STRING)
    private LetterState state;
}