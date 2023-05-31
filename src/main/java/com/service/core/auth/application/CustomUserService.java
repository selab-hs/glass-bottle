package com.service.core.auth.application;

import com.service.core.auth.domain.CustomUserDetails;
import com.service.core.auth.token.TokenProvider;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.JoinRequest;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomUserDetails.create(
            memberRepository.findById((long) Integer.parseInt(username)).get());
    }

    public String userLogin(JoinRequest joinRequest){
        User member = memberRepository.findByEmail(joinRequest.getEmail());
        return tokenProvider.createToken(member.getId(), member.getRoleType().getKey());
    }
}