package com.service.core.auth.filter.vo;

import org.springframework.stereotype.Component;

@Component
public class AccessLink {
    private static final String[] passLinks = {
        "/api/v1/members/signup",
        "/api/v1/auth/login",
        "/logout"
    };

    private static final String[] accessRoles = {"members","admin"};

    public String[] getPassLinks(){
        return passLinks;
    }

    public String[] getAccessRoles(){
        return accessRoles;
    }
}
