package com.service.core.letter.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.letter.vo.LetterState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long senderMbtiId;

    @NotNull
    private Long receiverMbtiId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Enumerated(EnumType.STRING)
    private LetterState state;

    public void updateLetterState(LetterState state) {
        this.state = state;
    }

    public Letter(Long senderMbtiId, Long receiverMbtiId, String title, String content, LetterState state) {
        this.senderMbtiId = senderMbtiId;
        this.receiverMbtiId = receiverMbtiId;
        this.title = title;
        this.content = content;
        this.state = state;
    }
}
