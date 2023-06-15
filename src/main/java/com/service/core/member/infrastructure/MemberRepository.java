package com.service.core.member.infrastructure;

import com.service.core.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByMbtiId(Long mbtiId);

    List<User> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}