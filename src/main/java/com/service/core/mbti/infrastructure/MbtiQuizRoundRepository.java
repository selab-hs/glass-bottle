package com.service.core.mbti.infrastructure;

import com.service.core.mbti.domain.MbtiQuizRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiQuizRoundRepository extends JpaRepository<MbtiQuizRound, Long> {
}
