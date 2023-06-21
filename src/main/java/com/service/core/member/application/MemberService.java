package com.service.core.member.application;

import com.service.core.common.utis.LocalDateTimeUtil;
import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.member.DuplicatedMemberException;
import com.service.core.error.exception.member.NotEqualsMemberInfoException;
import com.service.core.member.convert.MemberConvert;
import com.service.core.member.domain.User;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.request.UpdateMemberMbtiRequest;
import com.service.core.member.dto.response.UserInfo;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Lazy
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(CreateMemberRequest createMemberRequest) {
        memberRepository.findByEmail(createMemberRequest.getEmail())
            .ifPresent(a -> {
                throw new DuplicatedMemberException(ErrorMessage.DUPLICATED_MEMBER_INFO_ERROR);
            });
        memberRepository.save(MemberConvert.toEntity(createMemberRequest));
    }

    @Transactional
    public User createAdminMember(){
        User admin = MemberConvert.toAdmin();
        memberRepository.save(admin);
        return admin;
    }

    @Transactional
    public void updateMember(UserInfo user, UpdateMemberMbtiRequest request){
        User member = memberRepository.findById(user.getId()).orElseThrow(
            ()-> new NotEqualsMemberInfoException(ErrorMessage.NOT_EQUALS_MEMBER_INFO_ERROR)
        );
        member.update(request.getMbtiId());
        memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long uid){
        memberRepository.deleteById(uid);
    }

    @Transactional(readOnly = true)
    public User viewUser(Long userId){
            return memberRepository.findById(userId)
                .orElseThrow(
                    () -> new NotEqualsMemberInfoException(ErrorMessage.NOT_EQUALS_MEMBER_INFO_ERROR)
                );
    }

    @Transactional(readOnly = true)
    public String getYesterdayJoinUsers() {
        var joinUsers =  memberRepository.findAllByCreatedAtBetween(LocalDateTimeUtil.getYesterdayEightClock()
                , LocalDateTimeUtil.getTodayEightClock());
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