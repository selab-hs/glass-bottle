package com.service.core.member.infrastructure;

import com.service.core.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}