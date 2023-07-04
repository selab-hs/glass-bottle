package com.service.core.member.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.service.core.error.exception.member.DuplicatedMemberException;
import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.RoleType;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.request.UpdateMemberMbtiRequest;
import com.service.core.member.dto.response.UserInfo;
import com.service.core.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    public UserInfo userInfo;

    @BeforeEach
    void initUserInfo(){
        memberRepository.save(
            User.builder()
            .roleType(RoleType.USER)
            .mbtiId(1L)
            .password("1234")
            .email("test@naver.com")
            .build()
        );

       userInfo = UserInfo.builder()
            .id(1L)
            .email("test@naver.com")
            .mbtiId(1L)
            .roleType(String.valueOf(RoleType.USER))
            .build();
    }

    @Test
    void 회원가입_중복오류_TEST() throws Exception{
        //given
        CreateMemberRequest createMemberRequest1 = new CreateMemberRequest();
        createMemberRequest1.setEmail("hwang@naver.com");
        createMemberRequest1.setPassword("hwang1234");
        createMemberRequest1.setMbti(2L);

        CreateMemberRequest createMemberRequest2 = new CreateMemberRequest();
        createMemberRequest2.setEmail("hwang@naver.com");
        createMemberRequest2.setPassword("hwang1234");
        createMemberRequest2.setMbti(2L);

        //when
        memberService.createMember(createMemberRequest1);

        //then
        assertThrows(DuplicatedMemberException.class, ()->{
            memberService.createMember(createMemberRequest2);
        });
    }

    @Test
    void 회원정보_수정_TEST() throws Exception{
        //given
        UpdateMemberMbtiRequest updateMemberMbtiRequest = new UpdateMemberMbtiRequest(3L);

        //when
        memberService.updateMember(userInfo,updateMemberMbtiRequest);

        //then
        assertThat(memberRepository.findById(1L).get().getMbtiId()).isEqualTo(updateMemberMbtiRequest.getMbtiId());
    }
}