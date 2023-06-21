package com.service.core.member.infrastructure;

import com.service.core.member.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Cacheable(value = "targetUsers", key = "#mbtiId")
    List<User> findByMbtiId(Long mbtiId);

    @Cacheable(value = "allUsers", key = "'SimpleKey'")
    List<User> findAll();

    List<User> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}