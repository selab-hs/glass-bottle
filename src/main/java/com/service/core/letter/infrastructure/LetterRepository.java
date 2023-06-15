package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.Letter;
import com.service.core.letter.vo.LetterState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    Optional<Letter> findByState(LetterState state);

    List<Letter> findAllByState(LetterState state);

    List<Letter> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}