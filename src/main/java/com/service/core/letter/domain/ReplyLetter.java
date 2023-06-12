package com.service.core.letter.domain;

import com.service.core.member.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyLetter {
    @Id
    @Column(name = "reply_letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "send_letter_id")
    private SendLetter sendLetterId;

    @ManyToOne
    @JoinColumn(name = "sender_id") //답장 보내는 사람
    private User senderId;          //지금 로그인한 사람

    @ManyToOne
    @JoinColumn(name = "receiver_id") //답장 받는 사람
    private SendLetter receiverId;          //기존에 자신에게 편지 보냈던 유저에게 답장해야 함

    @ManyToOne
    @JoinColumn(name = "user_mbti") //로그인한 사람의 MBTI
    private User mbti;

    @Column(nullable = false)
    private String content;

    private String sendDate;
}
