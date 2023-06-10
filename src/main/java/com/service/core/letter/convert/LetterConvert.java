package com.service.core.letter.convert;

import com.service.core.letter.domain.Letter;
import com.service.core.letter.dto.request.SendLetterRequest;
import com.service.core.letter.dto.response.SendLetterResponse;
import com.service.core.member.domain.vo.MbtiType;
import org.springframework.stereotype.Component;

@Component
public class LetterConvert {
    public static Letter toEntity(SendLetterRequest sendLetterRequest){
        return Letter.builder()
                .mbti(MbtiType.of(sendLetterRequest.getMbti()))
                .content(sendLetterRequest.getContent())
                .sendDate(sendLetterRequest.getSendDate())
                .build();
    }

    public static SendLetterResponse toSendLetterResponse(Letter letter){
        return SendLetterResponse.builder()
                .mbti(letter.getMbti().name())
                .content(letter.getContent())
                .sendDate(letter.getSendDate())
                .build();
    }
}
