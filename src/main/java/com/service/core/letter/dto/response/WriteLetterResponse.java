package com.service.core.letter.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WriteLetterResponse {
    private Long letterId;
}