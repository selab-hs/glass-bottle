package com.service.core.letter.application;

import com.service.core.error.exception.letter.NotExistMbtiTargetException;
import com.service.core.member.domain.User;
import com.service.core.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class RandomSend {
    private final MemberRepository memberRepository;
    private static final int TOTAL_QUEUE_SIZE = 4;
    private static final int MAX_SEND_SIZE = 10;
    List<Queue<User>> userList = new ArrayList<>();
    List<User> result = new ArrayList<>();

    public List<User> randomizeTarget(Long targetMbtiId) {
        List<User> targetUsers = memberRepository.findByMbtiId(targetMbtiId);
        validateExistMbtiUser(targetUsers);

        for (int i = 0; i < TOTAL_QUEUE_SIZE; i++){
            Queue<User> userQueue = new LinkedList<>();
            userList.add(userQueue);
        }

        for (int i = 0; i < targetUsers.size(); i++) {
            userList.get(i % TOTAL_QUEUE_SIZE).offer(targetUsers.get(i));
        }

        for (int i = 0; i < MAX_SEND_SIZE; i++) {
            int randomIndex = (int) (Math.random()*(TOTAL_QUEUE_SIZE - 1));
            result.add(userList.get(randomIndex).poll());
        }

        result.removeAll(Collections.singletonList(null));
        return result;
    }

    private void validateExistMbtiUser(List<User> targetUsers) {
        //이건 exception이 아니라, 유저에게 안내 후 다시 대상을 선택하는 식으로 변경하고 싶음... 근데 어떻게 해야 하는지 모르겠음 ^^...
        if (targetUsers.isEmpty()) {
            throw new NotExistMbtiTargetException();
        }
    }
}
