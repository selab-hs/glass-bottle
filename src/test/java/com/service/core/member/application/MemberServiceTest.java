package com.service.core.member.application;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import com.service.core.error.exception.member.DuplicatedMemberException;
import com.service.core.error.exception.member.EmailFormatMismatchException;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.request.UpdateMemberMbtiRequest;
import com.service.core.member.dto.response.UserInfo;
import com.service.core.member.infrastructure.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Test
    @DisplayName("회원가입_정상_테스트")
    void createMember() throws Exception {
        // given
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setEmail("test@naver.com");
        createMemberRequest.setPassword("1234");
        createMemberRequest.setMbti(1L);

        // when
        when(memberRepository.findByEmail(createMemberRequest.getEmail()))
            .thenReturn(Optional.empty()); // 존재하지 않는 사용자를 가정

        // then
        assertDoesNotThrow(() -> memberService.createMember(createMemberRequest));
        }

    @Test
    @DisplayName("회원가입_이메일형식_일치_테스트")
    void emailFormTest() throws Exception {
        // given
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setEmail("test$1233.com");
        createMemberRequest.setPassword("1234");
        createMemberRequest.setMbti(1L);

        // when
        when(memberRepository.findByEmail(createMemberRequest.getEmail()))
            .thenReturn(Optional.empty()); // 존재하지 않는 사용자를 가정

        // then
        assertThrows(EmailFormatMismatchException.class,() -> memberService.createMember(createMemberRequest));
    }

    @Test
    @DisplayName("회원가입_중복_테스트")
    void duplicationMemberTest() throws Exception {
        // given
        CreateMemberRequest createMemberRequest = new CreateMemberRequest();
        createMemberRequest.setEmail("test@naver.com");
        createMemberRequest.setPassword("1234");
        createMemberRequest.setMbti(1L);

        // when
        when(memberRepository.findByEmail(createMemberRequest.getEmail()))
            .thenReturn(Optional.of(new User())); // 존재하지 않는 사용자를 가정

        // then
        assertThrows(DuplicatedMemberException.class , () -> memberService.createMember(createMemberRequest));
    }

    @Test
    @DisplayName("회원_mbti_id_변경_테스트")
    void updateMember(){
        //given
        UpdateMemberMbtiRequest updateMember = new UpdateMemberMbtiRequest(2L);
        Long USER_ID = 1L;
        UserInfo userInfo = UserInfo.builder().id(1L).build();

        when(memberRepository.findById(USER_ID)).thenReturn(Optional.of(new User()));

        assertDoesNotThrow(() -> memberService.updateMember(userInfo, updateMember));
    }
}