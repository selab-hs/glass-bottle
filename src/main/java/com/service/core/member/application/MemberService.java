package com.service.core.member.application;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.member.DuplicatedMemberException;
import com.service.core.error.exception.member.NotEqualsMemberInfoException;
import com.service.core.member.convert.MemberConvert;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Lazy
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(CreateMemberRequest createMemberRequest) {
        memberRepository.findByEmail(createMemberRequest.getEmail())
            .ifPresent(a -> {
                throw new DuplicatedMemberException(ErrorMessage.DUPLICATED_MEMBER_INFO_ERROR);
            });
            memberRepository.save(
                memberRepository.save(MemberConvert.toEntity(createMemberRequest)));
    }

    public User viewUser(Long userId){
            return memberRepository.findById(userId)
                .orElseThrow(
                    () -> new NotEqualsMemberInfoException(ErrorMessage.NOT_EQUALS_MEMBER_INFO_ERROR)
                );
    }

    @Transactional(readOnly = true)

    public String getYesterdayJoinUsers() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(8,0,1));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0,0));

        var joinUsers =  memberRepository.findAllByCreatedAtBetween(startDatetime, endDatetime);
        return usersToString(joinUsers);
    }

    private String usersToString(List<User> users) {
        if(users == null || users.isEmpty()) {
            return "전날 가입한 회원은 존재하지 않습니다.";
        }

        StringBuilder message = new StringBuilder();
        for(User user : users) {
            message.append("[ CreateTime : " + user.getCreatedAt() + ", userID : " + user.getId() + ", userMbtiID : " + user.getMbtiId() + " ] \n");
        }

        return message.toString();
    }
}