package com.service.core.mbti.infrastructure;

import com.service.core.mbti.domain.MbtiQuiz;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiQuizRepository extends JpaRepository<MbtiQuiz, Long> {
    Optional<MbtiQuiz> findById(Long id);
    List<MbtiQuiz> findByRoundId(Long roundId);
}
