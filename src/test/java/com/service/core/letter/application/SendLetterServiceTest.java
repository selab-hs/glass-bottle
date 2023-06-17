//package com.service.core.letter.application;
//
//import com.service.core.letter.infrastructure.LetterRepository;
//import com.service.core.member.convert.MemberConvert;
//import com.service.core.member.domain.User;
//import com.service.core.member.dto.request.CreateMemberRequest;
//import com.service.core.member.infrastructure.MemberRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//@Slf4j
//class SendLetterServiceTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    LetterRepository letterRepository;
//
//    @Test
//    public void senderIdMappingTest() {
//        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
//        createMemberRequest.setEmail("test@naver.com");
//        createMemberRequest.setPassword("password");
//        createMemberRequest.setMbti(1L);
//        User user = memberRepository.save(memberRepository.save(MemberConvert.toEntity(createMemberRequest)));
//    }
//}