package com.service.core.member.convert;

import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.Email;
import com.service.core.member.domain.vo.Password;
import com.service.core.member.domain.vo.RoleType;
import com.service.core.member.dto.request.CreateMemberRequest;
import com.service.core.member.dto.response.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class MemberConvert {

    public static User toEntity(CreateMemberRequest createMemberRequest){
        return User.builder()
            .email(new Email(createMemberRequest.getEmail()).getEmail())
            .password(new Password(createMemberRequest.getPassword()).getPassword())
            .mbtiId(createMemberRequest.getMbti())
            .roleType(RoleType.USER)
            .build();
    }

    public static User toAdmin(){
        return User.builder()
            .email("admin")
            .password("admin")
            .roleType(RoleType.ADMIN)
            .build();
    }

    public static UserInfo toUserInfo(User user){
        return UserInfo.builder()
            .id(user.getId())
            .email(user.getEmail())
            .mbtiId(user.getMbtiId())
            .roleType(user.getRoleType().getKey())
            .build();
    }
}