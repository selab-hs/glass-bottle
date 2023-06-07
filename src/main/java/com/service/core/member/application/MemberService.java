package com.service.core.member.application;

import com.service.core.member.convert.MemberConvert;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public String getYesterdayUsers() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(8,0,1));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0,0));
        StringBuilder message = new StringBuilder();

        var users = memberRepository.findAllByCreatedAtBetween(startDatetime, endDatetime);

        for(User a : users) {
            message.append("[ CreateTime : " + a.getCreatedAt() + ", userID : " + a.getId() + ", userMBTI : " + a.getMbti() + " ] \n");
        }

        return message.toString();
    }
}