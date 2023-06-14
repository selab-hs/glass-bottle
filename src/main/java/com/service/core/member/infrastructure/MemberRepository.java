package com.service.core.member.infrastructure;

import com.service.core.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByMbti(String mbti);
    List<User> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}