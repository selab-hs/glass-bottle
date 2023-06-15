package com.service.core.letter.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.letter.vo.LetterState;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderMbtiId;
    private Long receiverMbtiId;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private LetterState state;

    public void updateLetterState(LetterState state) {
        this.state = state;
    }
}
