package com.service.core.letter.application;

import com.service.core.error.exception.letter.NotExistMbtiTargetException;
import com.service.core.member.domain.User;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

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

        List<User> targetUsers = targeting(targetMbtiId);
        validateExistMbtiUser(targetUsers);

        return getUsers(targetUsers);
    }

    public Set<User> randomizeTarget() {
        result.clear();

        List<User> targetUsers = targeting();

        for (User target : targetUsers) {
            log.info("target.getMbtiId(): " + target.getMbtiId());
        }

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

    @Cacheable(value = "target", key = "#targetMbti")
    public List<User> targeting(Long targetMbti) {
        log.info("DB 조회");
        return memberRepository.findByMbtiId(targetMbti);
    }

    @Cacheable(value = "allUser")
    public List<User> targeting() {
        log.info("DB 조회");
        return memberRepository.findAll();
    }

    private void validateExistMbtiUser(List<User> targetUsers) {
        if (targetUsers.isEmpty()) {
            throw new NotExistMbtiTargetException();
        }
    }
}
