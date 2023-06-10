package com.service.core.letter.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.member.domain.vo.MbtiType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter extends BaseEntity {
    @Id
    @Column(name = "letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "mbti")
    @Enumerated(EnumType.STRING)
    private MbtiType mbti;

    @Column(nullable = false)
    private String content;

    private String sendDate;
}