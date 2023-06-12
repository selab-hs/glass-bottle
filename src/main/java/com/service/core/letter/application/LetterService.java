package com.service.core.letter.application;

import com.service.core.error.exception.letter.NotExistLetterException;
import com.service.core.letter.convert.LetterConvert;
import com.service.core.letter.domain.SendLetter;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.LetterResponse;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.domain.User;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final MemberRepository memberRepository;
    private final LetterRepository letterRepository;

    @Transactional
    public WriteLetterResponse writeLetter(WriteLetterRequest request, User user) {
        SendLetter sendLetter = SendLetter.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .senderId(user.getId())
                .senderMbti(user.getMbti().name())
                .build();

        letterRepository.save(sendLetter);
        return LetterConvert.toWriteLetterResponse(request, user);
    }

    public List<SendLetter> findAllLetters() {
        return letterRepository.findAll();
    }

    @Transactional
    public LetterResponse findLetterById(Long id) {
        var letter = letterRepository.findById(id).orElseThrow(NotExistLetterException::new);
        return LetterResponse.of(letter);
    }
}