package com.service.core.letter.application;

import com.service.core.letter.convert.LetterConvert;
import com.service.core.letter.domain.Letter;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.MbtiType;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final MemberRepository memberRepository;
    private final LetterRepository letterRepository;
    User user;

    @Transactional
    public WriteLetterResponse writeLetter(WriteLetterRequest request) {
        Letter letter = Letter.builder()
                //.senderId(user.getId())
                .mbti(MbtiType.of(request.getMbti()))
                .content(request.getContent())
                .sendDate(request.getSendDate())
                .build();
        letterRepository.save(letter);
        return LetterConvert.toWriteLetterResponse(request);
    }
}
