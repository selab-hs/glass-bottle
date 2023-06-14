package com.service.core.letter.application;

import com.service.core.member.domain.User;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class RandomSend {
    private final MemberRepository memberRepository;
    private static final int TOTAL_SIZE = 4;
    private static final int MAX_SEND_SIZE = 10;
    List<Queue<User>> queues = new ArrayList<>();
    List<User> resultUsers = new ArrayList<>();

    //보내는 사람이 지정한 MBTI 중 랜덤으로 대상 선정
    public List<User> randomizeTarget(String targetMbti) {
        List<User> targetUsers = memberRepository.findByMbti(targetMbti);

        for (int i = 0; i < TOTAL_SIZE; i++){
            Queue<User> userQueue = new LinkedList<>();
            queues.add(userQueue);
        }

        for (int i = 0; i < targetUsers.size(); i++) {
            queues.get(i % TOTAL_SIZE).offer(targetUsers.get(i));
        }

        for (int i = 0; i < MAX_SEND_SIZE; i++) {
            int randomIndex = (int) (Math.random()*(TOTAL_SIZE - 1));
            resultUsers.add(queues.get(randomIndex).poll());
        }

        return resultUsers;
    }
}
