package com.service.core.mbti.domain;

<<<<<<< HEAD
import com.service.core.common.domain.BaseEntity;
=======
>>>>>>> 2d44878 (feat: mbti domain setup)
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mbti_round")
<<<<<<< HEAD
public class MbtiQuizRound extends BaseEntity {
=======
public class MbtiQuizRound {
>>>>>>> 2d44878 (feat: mbti domain setup)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer round;

    private String description;
}
