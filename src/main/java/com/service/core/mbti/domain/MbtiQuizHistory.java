package com.service.core.mbti.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.mbti.domain.converter.MbtiResultConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mbti_quiz_history")
public class MbtiQuizHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "round_id")
    private Long roundId;


    @Convert(converter = MbtiResultConverter.class)
    private MbtiQuizHistory.MbtiResult result;

    private Long createdBy;

    public static class MbtiResult {
        private Integer seg;
        private String answer;
    }
}


