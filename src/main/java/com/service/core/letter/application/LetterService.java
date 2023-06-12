package com.service.core.letter.application;

import com.service.core.letter.convert.LetterConvert;
import com.service.core.letter.domain.SendLetter;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.domain.User;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                //.mbti(user)
                .build();
        letterRepository.save(sendLetter);
        System.out.println(user.getMbti());
        return LetterConvert.toWriteLetterResponse(request);
    }
}