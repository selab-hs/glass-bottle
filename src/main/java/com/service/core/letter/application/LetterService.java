package com.service.core.letter.application;

import com.service.core.common.utis.LocalDateTimeUtil;
import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.letter.*;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterService {
    private final LetterInvoiceRepository letterInvoiceRepository;
    private final LetterRepository letterRepository;
    private final RandomSend randomSend;

    @Transactional
    public void writeLetterToAppointTargetUsers(WriteLetterRequest request, UserInfo senderUser) {
        Letter letter = LetterConvert.toLetterEntity(request, senderUser);
        saveLetter(letter);

        WriteLetterResponse response = LetterConvert.toWriteLetterResponse(letter);
        appointTargetMbti(request, response, senderUser);
    }

    @Transactional
    public void writeLetterToAllUsers(WriteLetterRequest request, UserInfo senderUser) {
        Letter letter = LetterConvert.toLetterEntity2(request, senderUser);
        saveLetter(letter);

        WriteLetterResponse response = LetterConvert.toWriteLetterResponse2(letter);
        appointAllUsers(request, response, senderUser);
    }

    @Transactional
    public void appointTargetMbti(WriteLetterRequest request, WriteLetterResponse response, UserInfo senderUser) {
        Set<User> targets = randomSend.randomizeTarget(request.getReceiverMbtiId());
        for (User target : targets) {
            if (targets.size() == 1 && validateOneself(senderUser, target)) {
                throw new NotExistMbtiTargetException();
            }
            if (validateOneself(senderUser, target)) continue;
            letterInvoiceRepository.save(LetterConvert.toLetterInvoice(response, senderUser, target));
        }
    }

    @Transactional
    public void appointAllUsers(WriteLetterRequest request, WriteLetterResponse response, UserInfo senderUser) {
        Set<User> targets = randomSend.randomizeTarget();
        for (User target : targets) {
            if (targets.size() == 1 && validateOneself(senderUser, target)) {
                throw new NotExistMbtiTargetException();
            }
            if (validateOneself(senderUser, target)) continue;
            letterInvoiceRepository.save(LetterConvert.toLetterInvoice(response, senderUser, target));
        }
    }

    private boolean validateOneself(UserInfo userInfo, User user) {
        return validateEqualsId(user.getId(), userInfo.getId());
    }

    @Transactional
    public void replyLetter(ReplyLetterRequest request, UserInfo sender, Long letterId) {
        Long targetUserId = 0L;

        Optional<Letter> targetLetter = letterRepository.findById(letterId);

        validateReplyLetterState(targetLetter.get());
        validateReplyLetterRequest(sender, letterId);

        Long receiverMbtiId = targetLetter.get().getReceiverMbtiId();

        List<LetterInvoice> targetLetterInvoice = letterInvoiceRepository.findByLetterId(targetLetter.get().getId());

        for (LetterInvoice letterInvoice : targetLetterInvoice) {
            targetUserId = letterInvoice.getSenderUserId();
            if (validateEqualsId(sender.getId(), targetUserId)) {
                break;
            }
        }

        Letter letter = LetterConvert.toReplyLetterEntity(request, sender, receiverMbtiId);
        saveLetter(letter);

        targetLetter.get().updateLetterState(LetterState.RECEIVE_COMPLETE);
        saveLetter(targetLetter.get());

        ReplyLetterResponse response = LetterConvert.toReplyLetterResponse(letter);
        letterInvoiceRepository.save(LetterConvert.toReplyLetterInvoice(response, sender, targetUserId));
    }

    @Transactional
    public void deleteLetter(Long letterId, UserInfo user) {
        Letter targetLetter = letterRepository.findById(letterId).orElseThrow(NotExistLetterException::new);
        var letters =  letterInvoiceRepository.findBySenderUserIdAndLetterId(user.getId(), letterId);

        if (letters.isEmpty()) {
            throw new InvalidDeleteRequestException(ErrorMessage.INVALID_DELETE_REQUEST);
        }

        for (var letter : letters) {
            if (validateEqualsId(user.getId(), letter.getSenderUserId())
                    && validateEqualsId(letterId, letter.getLetterId())) {
                letterRepository.delete(targetLetter);
            }
        }
    }

    private boolean validateEqualsId(Long criterion, Long target) {
        return criterion.equals(target);
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

    @Transactional
    public void saveLetter(Letter letter) {
        letterRepository.save(letter);
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

    public void startReplyLetter(UserInfo userInfo, Long letterId){
        var id = validateReplyLetterRequest(userInfo, letterId);
        var letter = letterRepository.findById(id).get();

        letter.updateLetterState(LetterState.RECEIVE_WAITING);
        saveLetter(letter);
        replyLetterTimeTask(letter.getId());
    }

    private void validateReplyLetterState(Letter letter) {
        if(!LetterState.RECEIVE_WAITING.equals(letter.getState())) {
            throw new InvalidLetterStateException();
        }
    }

    @Transactional(readOnly = true)
    public Long validateReplyLetterRequest(UserInfo userInfo, Long letterId) {
        return letterInvoiceRepository.findByReceiverUserIdAndLetterId(userInfo.getId(), letterId)
                .map(LetterInvoice::getLetterId)
                .orElseThrow(InvalidReplyLetterRequestException::new);
    }

    private void replyLetterTimeTask(Long letterId) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Letter letter = letterRepository.findById(letterId).get();
                if(LetterState.RECEIVE_WAITING.equals(letter.getState())) {
                    letter.updateLetterState(LetterState.EXPIRATION);
                    saveLetter(letter);
                    log.info("letter Id : " + letter.getId() + " , 해당 답변은 만료되었습니다");
                }
            }
        };
        timer.schedule(timerTask, 1000*60*30);
    }
}