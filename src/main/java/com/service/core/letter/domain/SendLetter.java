package com.service.core.letter.domain;

import com.service.core.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendLetter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;

    private Long receiverId;

    private String senderMbti;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private boolean replyPossible;
}