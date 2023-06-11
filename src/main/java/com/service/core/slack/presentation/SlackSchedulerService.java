package com.service.core.slack.presentation;

import com.service.core.common.resttemplate.RestTemplateService;
import com.service.core.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class SlackSchedulerService {
    private final MemberService memberService;

    private final RestTemplateService restTemplateService;

    @Value("${logging.slack.uri.slack-glass-bottle}")
    private String letterUri;

    @Value("${logging.slack.uri.slack-join-member}")
    private String memberUri;

    @Value("${logging.slack.uri.slack-server-health}")
    private String serverHealthUri;


//    @Scheduled(cron = "0 0 8 * * *")
//    private void schedulerYesterdayLetter() {
//        로직 생성 이후 추가 예정
//
//    }

    @Scheduled(cron = "0 0 8 * * *")
    private void schedulerYesterdayJoinMember() {
        var response = restTemplateService.postToSlack(memberUri, "Slack Message", memberService.getYesterdayJoinUsers());
        log.info(String.valueOf(response));
    }

    @Scheduled(cron = "0 0 */1 * * *")
    private void schedulerServerStateCheck() {
        var health = restTemplateService.getToUri("http://localhost:8080/actuator/health");
        var response =restTemplateService.postToSlack(serverHealthUri, "Server Health", health);
        log.info(String.valueOf(response));
    }
}
