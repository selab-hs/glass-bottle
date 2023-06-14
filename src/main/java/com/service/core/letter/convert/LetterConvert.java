package com.service.core.letter.convert;

import com.service.core.letter.domain.Letter;
import com.service.core.letter.domain.LetterInvoice;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.member.domain.User;
import com.service.core.member.dto.response.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class LetterConvert {

    public static Letter toSendLetterEntity(WriteLetterRequest request, UserInfo senderUser){
       return Letter.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .senderMbtiId(senderUser.getMbtiId())
                .receiverMbtiId(request.getReceiverMbtiId())
                .build();
    }

    public static WriteLetterResponse toSendLetterResponse(Letter letter){
        return WriteLetterResponse.builder()
                .letterId(letter.getId())
                .build();
    }

    public static LetterInvoice toSendLetterLetterInvoice(
            WriteLetterResponse response,
            UserInfo senderUser,
            User target) {
        return LetterInvoice.builder()
                        .senderUserId(senderUser.getId())
                        .receiverUserId(target.getId())
                        .letterId(response.getLetterId())
                        .build();
    }
}