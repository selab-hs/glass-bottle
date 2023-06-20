package com.service.core.mbti.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.mbti.domain.converter.MbtiResultConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mbti_quiz_history")
public class MbtiQuizHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "round_id")
    private Long roundId;

    private Long userId;

    @Column(name = "mbti_metadata_id")
    private Long mbtiMetadataId;

    private Integer seg;
    private Integer answer;

    @Getter
    public static class MbtiResult {
        private Integer seg;
        private Integer answer;

        public MbtiResult(Integer seq, Integer answer) {
            this.seg = seq;
            this.answer = answer;
        }
    }
}