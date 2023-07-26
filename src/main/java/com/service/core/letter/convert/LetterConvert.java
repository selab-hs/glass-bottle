package com.service.core.letter.convert;

import com.service.core.letter.domain.Letter;
import com.service.core.letter.domain.LetterInvoice;
import com.service.core.letter.dto.request.ReplyLetterRequest;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.ReplyLetterResponse;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.vo.LetterState;
import com.service.core.member.domain.User;
import com.service.core.member.dto.response.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class LetterConvert {

    public static Letter toLetterEntity(WriteLetterRequest request, UserInfo sender) {
        if (request.getReceiverMbtiId() == null) {
            return Letter.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .senderMbtiId(sender.getMbtiId())
                    .receiverMbtiId(0L)
                    .state(LetterState.ACTIVE)
                    .build();
        }
        return Letter.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .senderMbtiId(sender.getMbtiId())
                .receiverMbtiId(request.getReceiverMbtiId())
                .state(LetterState.ACTIVE)
                .build();
    }

    public static Letter toReplyLetterEntity(ReplyLetterRequest request, UserInfo sender, Long receiverMbtiId) {
        return Letter.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .senderMbtiId(sender.getMbtiId())
                .receiverMbtiId(receiverMbtiId)
                .state(LetterState.RECEIVE_COMPLETE)
                .build();
    }

    public static WriteLetterResponse toWriteLetterResponse(Letter letter) {
        return WriteLetterResponse.builder()
                .senderMbtiId(letter.getSenderMbtiId())
                .receiverMbtiId(letter.getReceiverMbtiId())
                .letterId(letter.getId())
                .build();
    }

    public static ReplyLetterResponse toReplyLetterResponse(Letter letter) {
        return ReplyLetterResponse.builder()
                .letterId(letter.getId())
                .build();
    }

    public static LetterInvoice toLetterInvoice(
            WriteLetterResponse response,
            UserInfo senderUser,
            User target) {
        return LetterInvoice.builder()
                .senderUserId(senderUser.getId())
                .receiverUserId(target.getId())
                .letterId(response.getLetterId())
                .build();
    }

    public static LetterInvoice toReplyLetterInvoice(
            ReplyLetterResponse response,
            UserInfo senderUser,
            Long targetId) {
        return LetterInvoice.builder()
                .senderUserId(senderUser.getId())
                .receiverUserId(targetId)
                .letterId(response.getLetterId())
                .build();
    }
}