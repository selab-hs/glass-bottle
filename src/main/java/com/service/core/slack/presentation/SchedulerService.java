package com.service.core.slack.presentation;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.service.core.error.exception.BusinessException;
import com.service.core.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {
    private final MemberService memberService;

    @Value("${logging.slack.uri.slack-glass-bottle}")
    private String letterUri;

    @Value("${logging.slack.uri.slack-join-member}")
    private String memberUri;


//    @Scheduled(cron = "0 0 8 * * *")
//    private void schedulerYesterdayLetter() {
//        로직 생성 이후 추가 예정
//        sendSlackMessage(letterUri, "test");
//            log.info("전일 유리병 편지 슬랙 로그 출력");
//    }

    @Scheduled(cron = "0 0 8 * * *")
    private void schedulerYesterdayJoinMember() {
        sendSlackMessage(memberUri, memberService.getYesterdayUsers());
        log.info("전일 회원가입유저 슬랙 로그 출력");
    }

    private WebhookResponse sendSlackMessage(String uri, String message) {
        try {
            Payload payload = Payload.builder().text(message).build();
            WebhookResponse response = Slack.getInstance().send(uri, payload);

            return response;
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
