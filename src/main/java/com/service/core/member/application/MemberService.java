package com.service.core.member.application;

import com.service.core.member.convert.MemberConvert;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Lazy
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(CreateMemberRequest createMemberRequest){
        memberRepository.save(memberRepository.save(MemberConvert.toEntity(createMemberRequest)));
    }

    public User viewUser(Long userId){
        return memberRepository.findById(userId).get();
    }
}