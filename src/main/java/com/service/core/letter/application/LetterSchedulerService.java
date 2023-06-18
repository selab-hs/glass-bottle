package com.service.core.letter.application;

import com.service.core.common.utis.LocalDateTimeUtil;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.letter.vo.LetterState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LetterSchedulerService {
    private final LetterService letterService;
    private final LetterRepository letterRepository;

    @Scheduled(cron = "0 0 0 * * *")
    private void autoDeleteLetters() {
        letterRepository.deleteAllInBatchByCreatedAtLessThanEqual(LocalDateTime.now().minusDays(30));
    }

    @Scheduled(cron = "0 */1 * * * *")
    private void schedulerYesterdayLetter() {
        validateExpirationLetterState();
    }

    private void validateExpirationLetterState(){
        letterService.findLetterState(LetterState.ACTIVE)
                .stream()
                .filter(letter -> LocalDateTimeUtil.getNow().isAfter(letter.getCreatedAt().plusDays(1)))
                .forEach(letter -> {
                    letter.updateLetterState(LetterState.EXPIRATION);
                    letterService.saveLetter(letter);
                    log.info("해당 편지 만료 : " + letter.getId());
                });
    }
}
