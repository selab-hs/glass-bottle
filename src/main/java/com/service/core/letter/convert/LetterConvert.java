package com.service.core.letter.convert;

import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import org.springframework.stereotype.Component;

@Component
public class LetterConvert {
    public static WriteLetterResponse toWriteLetterResponse(WriteLetterRequest writeLetterRequest){
        return WriteLetterResponse.builder()
                .mbti(writeLetterRequest.getMbti())
                .content(writeLetterRequest.getContent())
                .sendDate(writeLetterRequest.getSendDate())
                .build();
    }
}
