package com.service.core.member.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.member.domain.vo.MbtiType;
import com.service.core.member.domain.vo.RoleType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @Column(name = "user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_mbti")
    @Enumerated(EnumType.STRING)
    private MbtiType mbti;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}