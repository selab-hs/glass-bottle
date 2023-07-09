package com.service.core.auth.application;

import static org.junit.jupiter.api.Assertions.*;
import com.service.core.auth.domain.UserDetail;
import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.RoleType;
import com.service.core.member.infrastructure.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthUserServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private AuthUserService authUserService;

    @Test
    void loadUserByUsername_인증_인가(){
        //given
        User user =  User.builder()
            .roleType(RoleType.USER)
            .mbtiId(1L)
            .password("1234")
            .email("test@naver.com")
            .build();

        //when
        when(memberRepository.findById(any())).thenReturn(Optional.of(user));

        UserDetail userDetail = authUserService.loadUserByUsername(String.valueOf(1L));

        //then
        assertEquals(user.getEmail(), userDetail.getEmail());

    }
}