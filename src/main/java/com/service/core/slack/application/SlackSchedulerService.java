package com.service.core.slack.application;

import com.service.core.common.properties.SlackProperties;
import com.service.core.common.resttemplate.RestTemplateService;
import com.service.core.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SlackSchedulerService {
    private final MemberService memberService;

    private final RestTemplateService restTemplateService;

    private final SlackProperties slackProperties;


//    @Scheduled(cron = "0 0 8 * * *")
//    private void schedulerYesterdayLetter() {
//        var response = restTemplateService.postRequestToSlack(slackProperties.slackGlassBottle(), "Slack Message", //편지-발송 서비스 String 값 받아올 예정);
//        log.info(String.valueOf(response));
//    }

    @Scheduled(cron = "0 0 8 * * *")
    private void schedulerYesterdayJoinMember() {
        var response = restTemplateService.postRequestToSlack(slackProperties.slackJoinMember(), "Slack Message", memberService.getYesterdayJoinUsers());
        log.info(String.valueOf(response));
    }

    @Scheduled(cron = "0 0 */1 * * *")
    private void schedulerServerStateCheck() {
        var health = restTemplateService.getToUri("http://localhost:8080/actuator/health");
        var response =restTemplateService.postRequestToSlack(slackProperties.slackServerHealth(), "Server Health", health);
        log.info(String.valueOf(response));
    }
}
