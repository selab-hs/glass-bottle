package com.service.core.member.domain.vo;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER("ROLE_USER","사용자"),
    ADMIN("ROLE_ADMIN","관리자"),
    GUEST("GUEST", "게스트 권한");

    private final String key;
    private final String title;

    public static RoleType of(String key){
        return Arrays.stream(RoleType.values())
            .filter(r -> r.getKey().equals(key))
            .findAny()
            .orElse(GUEST);
    }
}