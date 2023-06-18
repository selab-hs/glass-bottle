package com.service.core.member.domain;

import com.service.core.common.domain.BaseEntity;
import com.service.core.member.domain.vo.RoleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Table(name = "member")
public class User extends BaseEntity {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mbti")
    private Long mbtiId;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}