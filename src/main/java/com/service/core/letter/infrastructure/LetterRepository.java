package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    Optional<Letter> findByReplyPossible(boolean True);
}