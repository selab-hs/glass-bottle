package com.service.core.member.convert;

import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.MbtiType;
import com.service.core.member.domain.vo.RoleType;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.response.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class MemberConvert {

    public static User toEntity(CreateMemberRequest createMemberRequest){
        return User.builder()
            .email(createMemberRequest.getEmail())
            .password(createMemberRequest.getPassword())
            .mbti(MbtiType.of(createMemberRequest.getMbti()))
            .roleType(RoleType.USER)
            .build();
    }

    public static UserInfo toUserInfo(User user){
        return UserInfo.builder()
            .id(user.getId())
            .email(user.getEmail())
            .mbti(user.getMbti().name())
            .roleType(user.getRoleType().getKey())
            .build();
    }
}