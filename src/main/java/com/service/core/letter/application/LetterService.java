package com.service.core.letter.application;

import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final MemberRepository memberRepository;
    private final LetterRepository letterRepository;
}
