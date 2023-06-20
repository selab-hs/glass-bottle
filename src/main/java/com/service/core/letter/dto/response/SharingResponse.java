package com.service.core.letter.dto.response;

public class SharingResponse {
    String senderMbti;
    String senderTitle;
    String senderContent;
    String receiverMbti;
    String receiverTitle;
    String receiverContent;

    public SharingResponse(WriteLetterResponse senderLetter, WriteLetterResponse receiverLetter) {
        this.senderMbti = String.valueOf(senderLetter.getSenderMbtiId());
        this.senderTitle = senderLetter.getTitle();
        this.senderContent = senderLetter.getContent();
        this.receiverMbti = String.valueOf(receiverLetter.getReceiverMbtiId());
        this.receiverTitle = receiverLetter.getTitle();
        this.receiverContent = receiverLetter.getContent();
    }

}
