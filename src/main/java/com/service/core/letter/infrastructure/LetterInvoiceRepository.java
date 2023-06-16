package com.service.core.letter.infrastructure;

import com.service.core.letter.domain.LetterInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LetterInvoiceRepository extends JpaRepository<LetterInvoice, Long> {
    List<LetterInvoice> findByLetterId(Long letterId);

    Optional<LetterInvoice> findByReceiverUserIdAndLetterId(Long ReceiverUserid, Long letterId);

    List<LetterInvoice> findBySenderUserIdAndLetterId(Long senderUserId, Long letterId);
}
