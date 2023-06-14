package com.service.core.letter.application;

import com.service.core.error.exception.letter.NotExistLetterException;
import com.service.core.letter.convert.LetterConvert;
import com.service.core.letter.domain.Letter;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterInvoiceRepository;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.domain.User;
import com.service.core.member.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final LetterInvoiceRepository letterInvoiceRepository;
    private final LetterRepository letterRepository;
    private final RandomSend randomSend;

    @Transactional
    public Letter writeLetter(WriteLetterRequest request, UserInfo senderUser) {
        Letter letter = LetterConvert.toSendLetterEntity(request, senderUser);
        letterRepository.save(letter);

        WriteLetterResponse response = LetterConvert.toSendLetterResponse(letter);
        appointTargetMbti(request, response, senderUser);

        return letter;
    }

    @Transactional
    public void appointTargetMbti(WriteLetterRequest request, WriteLetterResponse response, UserInfo senderUser) {
        List<User> targets = randomSend.randomizeTarget(request.getReceiverMbtiId());

        for (User target : targets) {
            if (validateOneself(senderUser, target)) continue;
            letterInvoiceRepository.save(LetterConvert.toSendLetterLetterInvoice(response, senderUser, target));
        }
    }

    private boolean validateOneself(UserInfo user, User target) {
        if (target.getId().equals(user.getId())) {
            return true;
        }
        return false;
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
}