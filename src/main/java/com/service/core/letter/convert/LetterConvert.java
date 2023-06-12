package com.service.core.letter.convert;

import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.member.domain.User;
import org.springframework.stereotype.Component;

@Component
public class LetterConvert {
    public static WriteLetterResponse toWriteLetterResponse(WriteLetterRequest writeLetterRequest, User user){
        return WriteLetterResponse.builder()
                .title(writeLetterRequest.getTitle())
                .content(writeLetterRequest.getContent())
                .senderMbti(user.getMbti().name())
                .build();
    }
}