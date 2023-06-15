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
    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    private Long senderMbtiId;

    private Long receiverMbtiId;

    @Enumerated(EnumType.STRING)
    private LetterState state;
}