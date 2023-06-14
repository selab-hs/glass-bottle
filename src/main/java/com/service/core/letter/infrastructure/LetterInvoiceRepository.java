package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.LetterInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterInvoiceRepository extends JpaRepository<LetterInvoice, Long> {

    List<LetterInvoice> findByReceiverUserId(Long receiverUserId);
}
