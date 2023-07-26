package com.service.core.letter.application;

import com.service.core.error.exception.letter.NotExistMbtiTargetException;
import com.service.core.member.domain.User;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class RandomSend {
    private final MemberRepository memberRepository;
    private static final int MAX_SEND_SIZE = 10;
    Set<User> result = new HashSet<>();

    public Set<User> randomizeTarget(Long targetMbtiId) {
        result.clear();

        List<User> targetUsers = findUsers(targetMbtiId);
        validateExistMbtiUser(targetUsers);

        return getUsers(targetUsers);
    }

    public Set<User> randomizeTarget() {
        result.clear();

        List<User> targetUsers = findUsers();
        return getUsers(targetUsers);
    }

    private Set<User> getUsers(List<User> targetUsers) {
        Random random = new Random(System.currentTimeMillis());

        while (result.size() < MAX_SEND_SIZE) {
            if (targetUsers.size() < MAX_SEND_SIZE) {
                result.addAll(targetUsers);
                break;
            }
            result.add(targetUsers.get(random.nextInt(targetUsers.size())));
        }

        result.removeAll(Collections.singletonList(null));
        return result;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "targetUsers", key = "#mbtiId")
    public List<User> findUsers(Long targetMbti) {
        log.info("대상 MBTI 유저 목록 조회");
        return memberRepository.findByMbtiId(targetMbti);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "allUsers", key = "'SimpleKey'")
    public List<User> findUsers() {
        log.info("전체 유저 목록 조회");
        return memberRepository.findAll();
    }

    private void validateExistMbtiUser(List<User> targetUsers) {
        if (targetUsers.isEmpty()) {
            throw new NotExistMbtiTargetException();
        }
    }
}
