package com.service.core.letter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LettersResponse {
    private List<LetterResponse> letters;
}
