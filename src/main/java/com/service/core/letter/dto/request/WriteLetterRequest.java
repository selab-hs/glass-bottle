package com.service.core.letter.dto.request;

import com.service.core.letter.vo.LetterState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WriteLetterRequest {
    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(min = 1, max = 100, message = "제목은 100자 이내로 입력 가능합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(min = 1, max = 1000, message = "내용은 1000자 이내로 입력 가능합니다.")
    private String content;

    private Long senderMbtiId;

    private Long receiverMbtiId;

    @Enumerated(EnumType.STRING)
    private LetterState state;
}