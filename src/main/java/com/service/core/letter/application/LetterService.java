package com.service.core.letter.application;

import com.service.core.common.utis.LocalDateTimeUtil;
import com.service.core.error.exception.letter.InvalidLetterStateException;
import com.service.core.error.exception.letter.InvalidReplyLetterRequestException;
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
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterService {
    private final LetterInvoiceRepository letterInvoiceRepository;
    private final LetterRepository letterRepository;
    private final RandomSend randomSend;
    private final MemberRepository memberRepository;

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
        if (target.getId().equals(user.getId())) {
            return true;
        }
        return false;
    }

    @Transactional
    public void replyLetter(ReplyLetterRequest request, UserInfo sender, Long letterId) {
        Long targetUserId = Long.valueOf(0);

        Optional<Letter> targetLetter = letterRepository.findById(letterId);

        validateReplyLetterState(targetLetter.get());
        validateReplyLetterRequest(sender, letterId);

        Long receiverMbtiId = targetLetter.get().getReceiverMbtiId();

        List<LetterInvoice> targetLetterInvoice = letterInvoiceRepository.findByLetterId(targetLetter.get().getId());

        for (int i = 0; i < targetLetterInvoice.size(); i++) {
            targetUserId = targetLetterInvoice.get(i).getSenderUserId();
            if (targetUserId.equals(sender.getId())) {
                break;
            }
        }

        Letter letter = LetterConvert.toReplyLetterEntity(request, sender, receiverMbtiId);
        letterRepository.save(letter);
        targetLetter.get().updateLetterState(LetterState.RECEIVE_COMPLETE);
        letterRepository.save(targetLetter.get());

        ReplyLetterResponse response = LetterConvert.toReplyLetterResponse(letter);
        letterInvoiceRepository.save(LetterConvert.toReplyLetterInvoice(response, sender, targetUserId));
    }

    @Transactional
    public List<WriteLetterResponse> findAllLetters() {
        List<WriteLetterResponse> letters = letterRepository.findAll()
                .stream()
                .map(WriteLetterResponse::of)
                .collect(Collectors.toList());
        return letters;
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
        var letters =  letterRepository.findAllByCreatedAtBetween(LocalDateTimeUtil.getYesterdayEightClock()
                , LocalDateTimeUtil.getTodayEightClock());
        return lettersToString(letters);
    }

    private String lettersToString(List<Letter> letters) {
        if(letters == null || letters.isEmpty()) {
            return "전날 작성된 편지는 존재하지 않습니다.";
        }

        StringBuilder message = new StringBuilder();
        for(Letter letter : letters) {
            message.append("[ CreateTime : " + letter.getCreatedAt() + ", letterId : " + letter.getId() + ", letterState : " + letter.getState() + " ] \n");
        }

        return message.toString();
    }


    public void startReplyLetter(UserInfo userInfo, Long letterId){
        var id = validateReplyLetterRequest(userInfo, letterId);
        var letter = letterRepository.findById(id).get();
        letter.updateLetterState(LetterState.RECEIVE_WAITING);
        letterRepository.save(letter);

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
                .map(a -> a.getLetterId())
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
                    letterRepository.save(letter);
                    log.info("letter Id : " + letter.getId() + " , 해당 답변은 만료되었습니다");
                }
            }
        };

        timer.schedule(timerTask, 1000*60*30);
    }
}