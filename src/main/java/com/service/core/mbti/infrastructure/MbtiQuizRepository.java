package com.service.core.mbti.infrastructure;

import com.service.core.mbti.domain.MbtiQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiQuizRepository extends JpaRepository<MbtiQuiz, Long> {
}
