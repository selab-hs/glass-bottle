package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.Letter;
import com.service.core.letter.vo.LetterState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    List<Letter> findAllByState(LetterState state);

    List<Letter> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);

    void deleteAllInBatchByCreatedAtLessThanEqual(LocalDateTime localDateTime);
}