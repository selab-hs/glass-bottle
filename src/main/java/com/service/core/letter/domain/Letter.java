package com.service.core.letter.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.letter.vo.LetterState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(max=100, message = "제목은 100자 이내로 입력 가능합니다.")
    private String title;

    @NotNull
    @Size(max=1000, message = "내용은 1000자 이내로 입력 가능합니다.")
    private String content;

    @Enumerated(EnumType.STRING)
    private LetterState state;

    public void updateLetterState(LetterState state) {
        this.state = state;
    }
}
