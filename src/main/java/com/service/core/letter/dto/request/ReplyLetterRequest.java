package com.service.core.letter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyLetterRequest {
    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(max=100, message = "제목은 100자 이내로 입력 가능합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(max=1000, message = "내용은 1000자 이내로 입력 가능합니다.")
    private String content;

    private Long letterId;

    private Long senderMbtiId;

    private Long receiverMbtiId;
}
