package com.service.core.auth.application;

import com.service.core.auth.domain.UserDetail;
import com.service.core.auth.token.TokenProvider;
import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.member.NotEqualsMemberInfoException;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.JoinRequest;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public UserDetail loadUserByUsername(String username){
        return UserDetail.create(
            memberRepository.findById((long) Integer.parseInt(username)).get());
    }

    @Transactional
    public String userLogin(JoinRequest joinRequest){
        User member = memberRepository.findByEmailAndPassword(
            joinRequest.getEmail(),
            joinRequest.getPassword()).orElseThrow(
            () -> new NotEqualsMemberInfoException(ErrorMessage.NOT_EQUALS_MEMBER_INFO_ERROR)
        );
        return tokenProvider.createToken(member.getId(), member.getRoleType().getKey());
    }
}