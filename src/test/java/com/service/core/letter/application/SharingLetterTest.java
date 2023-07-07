package com.service.core.letter.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.core.letter.domain.Letter;
import com.service.core.letter.dto.response.SharingResponse;
import com.service.core.letter.dto.response.WriteLetterResponse;
import com.service.core.letter.infrastructure.LetterRepository;
import com.service.core.member.application.MemberService;
import com.service.core.member.convert.MemberConvert;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.response.UserInfo;
import com.service.core.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SharingLetterTest {
    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    private LetterService letterservice;

    @Mock
    private LetterRepository letterrepository;

//    @BeforeEach
//    public void setUp() {
//        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
//        createMemberRequest.setEmail("test@naver.com");
//        createMemberRequest.setPassword("1234");
//        createMemberRequest.setMbti(1L);
//
//        CreateMemberRequest createMemberRequest2 = new CreateMemberRequest();
//        createMemberRequest2.setEmail("test2@naver.com");
//        createMemberRequest2.setPassword("1234");
//        createMemberRequest2.setMbti(2L);
//
//        memberRepository.save(MemberConvert.toEntity(createMemberRequest));
//        memberRepository.save(MemberConvert.toEntity(createMemberRequest2));
//
//        System.out.println(memberService.viewUser(1L).getEmail());
//        // 이후 편지 작성 테스트 1->2
//    }



//    @Test
//    @DisplayName("편지 공유 통합테스트")
//    void sharingLetter2() {
//
//
//        String qrString = letterservice.sharingLetter(getUserInfo(), 1L, 2L);
//    }
//
//    private UserInfo getUserInfo() {
//        return UserInfo.builder()
//                .id(1L)
//                .email("test@naver.com")
//                .mbtiId(1L)
//                .roleType("ROLE_USER")
//                .build();
//    }

    @Test
    @DisplayName("편지 공유 단위테스트")
    void sharingLetter() throws Exception {
        //given
        WriteLetterResponse senderLetter = WriteLetterResponse.builder()
                .title("testTitle1")
                .content("testContent1")
                .senderMbtiId(1L)
                .build();

        WriteLetterResponse receiveLetter = WriteLetterResponse.builder()
                .title("testTitle2")
                .content("testContent2")
                .receiverMbtiId(2L)
                .build();

        //when
        String sharingLetterToJson = new ObjectMapper().writeValueAsString(new SharingResponse(senderLetter, receiveLetter));
        String data = letterservice.convertQRString(sharingLetterToJson);

        //then
        assertTrue(() -> data != null && !data.isEmpty());
    }
}
