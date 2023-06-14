package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.LetterInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterInvoiceRepository extends JpaRepository<LetterInvoice, Long> {
}
