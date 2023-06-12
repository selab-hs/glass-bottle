package com.service.core.letter.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.member.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendLetter extends BaseEntity {
    @Id
    @Column(name = "send_letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id") //현재 로그인된 회원의 정보 받아와야 함
    private User senderId;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverId;

    @ManyToOne
    @JoinColumn(name = "user_mbti")
    private User mbti;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String sendDate;

    private boolean replyPossible;
}