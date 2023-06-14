package com.service.core.letter.application;

import com.service.core.letter.convert.LetterConvert;
import com.service.core.letter.domain.Letter;
import com.service.core.letter.domain.LetterInvoice;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterInvoiceRepository;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.domain.User;
import com.service.core.member.dto.response.UserInfo;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final MemberRepository memberRepository;
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
    public void appointTargetMbti(WriteLetterRequest request, WriteLetterResponse response, UserInfo user) {
        List<User> targets = randomSend.randomizeTarget(request.getReceiverMbtiId());
        for (User target : targets) {
            letterInvoiceRepository.save(
                    LetterInvoice.builder()
                            .senderUserId(user.getId())
                            .receiverUserId(target.getId())
                            .letterId(response.getLetterId())
                            .build()
            );
        }
    }

/*    @Transactional
    public List<LetterInvoice> findAllLetters() {
        return letterInvoiceRepository.findAll();
    }

    @Transactional
    public LetterResponse findLetterById(Long id) {
        var letter = letterRepository.findById(id).orElseThrow(NotExistLetterException::new);
        return LetterResponse.of(letter);
    }*/
}