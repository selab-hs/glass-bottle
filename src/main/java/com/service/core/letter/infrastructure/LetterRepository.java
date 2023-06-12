package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.SendLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<SendLetter, Long> {

}