package com.service.core.letter.application;

import com.service.core.common.utis.LocalDateTimeUtil;
import com.service.core.error.exception.letter.NotExistLetterException;
import com.service.core.letter.convert.LetterConvert;
import com.service.core.letter.domain.Letter;
import com.service.core.letter.domain.LetterInvoice;
import com.service.core.letter.dto.request.ReplyLetterRequest;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.ReplyLetterResponse;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterInvoiceRepository;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.letter.vo.LetterState;
import com.service.core.member.domain.User;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final LetterInvoiceRepository letterInvoiceRepository;
    private final LetterRepository letterRepository;
    private final RandomSend randomSend;

    @Transactional
    public void writeLetter(WriteLetterRequest request, UserInfo senderUser) {
        Letter letter = LetterConvert.toLetterEntity(request, senderUser);
        letterRepository.save(letter);

        WriteLetterResponse response = LetterConvert.toWriteLetterResponse(letter);
        appointTargetMbti(request, response, senderUser);
    }

    @Transactional
    public void appointTargetMbti(WriteLetterRequest request, WriteLetterResponse response, UserInfo senderUser) {
        List<User> targets = randomSend.randomizeTarget(request.getReceiverMbtiId());

        for (User target : targets) {
            if (validateOneself(senderUser, target)) continue;
            letterInvoiceRepository.save(LetterConvert.toLetterInvoice(response, senderUser, target));
        }
    }

    private boolean validateOneself(UserInfo user, User target) {
        return target.getId().equals(user.getId());
    }

    @Transactional
    public void replyLetter(ReplyLetterRequest request, UserInfo sender, Long letterId) {
        Long targetUserId = 0L;

        Optional<Letter> targetLetter = letterRepository.findById(letterId);
        Long receiverMbtiId = targetLetter.get().getReceiverMbtiId();

        List<LetterInvoice> targetLetterInvoice = letterInvoiceRepository.findByLetterId(targetLetter.get().getId());

        for (LetterInvoice letterInvoice : targetLetterInvoice) {
            targetUserId = letterInvoice.getSenderUserId();
            if (targetUserId.equals(sender.getId())) {
                break;
            }
        }

        Letter letter = LetterConvert.toReplyLetterEntity(request, sender, receiverMbtiId);
        letterRepository.save(letter);

        ReplyLetterResponse response = LetterConvert.toReplyLetterResponse(letter);
        letterInvoiceRepository.save(LetterConvert.toReplyLetterInvoice(response, sender, targetUserId));
    }

    @Transactional
    public List<WriteLetterResponse> findAllLetters() {
        return letterRepository.findAll()
                .stream()
                .map(WriteLetterResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public WriteLetterResponse findLetterById(Long id) {
        var letter = letterRepository.findById(id).orElseThrow(NotExistLetterException::new);
        return WriteLetterResponse.of(letter);
    }

    @Transactional(readOnly = true)
    public List<Letter> findLetterState(LetterState state) {
        return letterRepository.findAllByState(state);
    }

    @Transactional(readOnly = true)
    public String getYesterdayLetters() {
        var letters =  letterRepository
                .findAllByCreatedAtBetween(
                        LocalDateTimeUtil
                                .getYesterdayEightClock()
                        , LocalDateTimeUtil
                                .getTodayEightClock());
        return lettersToString(letters);
    }

    private String lettersToString(List<Letter> letters) {
        if(letters == null || letters.isEmpty()) {
            return "전날 작성된 편지는 존재하지 않습니다.";
        }

        StringBuilder message = new StringBuilder();
        for(Letter letter : letters) {
            message.append("[ CreateTime : ").append(letter.getCreatedAt()).append(", letterId : ").append(letter.getId()).append(", letterState : ").append(letter.getState()).append(" ] \n");
        }

        return message.toString();
    }
}