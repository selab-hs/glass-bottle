package com.service.core.auth.domain;

import com.service.core.member.domain.User;
import com.service.core.member.domain.vo.MbtiType;
import com.service.core.member.domain.vo.RoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail {

    private Long id;
    private String email;
    private String password;
    private RoleType roleType;
    private MbtiType mbtiType;
    public static UserDetail create(User user){
        return new UserDetail(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            RoleType.USER,
            user.getMbti()
        );
    }
}