/*
package com.service.core.letter.application;

import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.convert.MemberConvert;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
@Slf4j
class SendLetterServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LetterRepository letterRepository;
    private static final int MAX_SEND_SIZE = 10;

    @Test
    public void senderIdMappingTest() {
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setEmail("test@naver.com");
        createMemberRequest.setPassword("password");
        createMemberRequest.setMbti(1L);
        User user = memberRepository.save(memberRepository.save(MemberConvert.toEntity(createMemberRequest)));
    }

    @Test
    public void randomize() {

        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());

        while (randomNumbers.size() < MAX_SEND_SIZE) {
            randomNumbers.add(random.nextInt(4));
        }
        log.info("randomNumbers: " + randomNumbers);

    }
}*/
