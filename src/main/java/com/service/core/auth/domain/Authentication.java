package com.service.core.auth.domain;

import com.service.core.member.domain.vo.RoleType;
import lombok.Getter;

@Getter
public class Authentication {
    private UserDetail userDetail;
    private RoleType roleType;

    public Authentication(UserDetail userDetail, RoleType roleType){
        this.userDetail = userDetail;
        this.roleType =roleType;
    }
}
