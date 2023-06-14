package com.service.core.member.infrastructure;

import com.service.core.member.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}