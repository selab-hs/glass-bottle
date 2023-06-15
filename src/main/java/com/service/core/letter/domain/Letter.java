package com.service.core.letter.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderMbtiId;
    private Long receiverMbtiId;
    private String title;
    private String content;
    private boolean replyPossible;
}
