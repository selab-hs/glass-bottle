package com.service.core.slack.application;

import com.service.core.common.properties.SlackProperties;
import com.service.core.common.resttemplate.RestTemplateService;
import com.service.core.letter.application.LetterService;
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

    private final LetterService letterService;

    private final RestTemplateService restTemplateService;

    private final SlackProperties slackProperties;


    @Scheduled(cron = "0 0 8 * * *")
    private void schedulerYesterdayLetter() {
        var response = restTemplateService.postRequestToSlack(slackProperties.slackGlassBottle(), "Write Letters", letterService.getYesterdayLetters());
        log.info(String.valueOf(response));
    }

    @Scheduled(cron = "0 0 8 * * *")
    private void schedulerYesterdayJoinMember() {
        var response = restTemplateService.postRequestToSlack(slackProperties.slackJoinMember(), "Join Users", memberService.getYesterdayJoinUsers());
        log.info(String.valueOf(response));
    }

    @Scheduled(cron = "0 0 */1 * * *")
    private void schedulerServerStateCheck() {
        var health = restTemplateService.getToUri("http://localhost:8080/actuator/health");
        var response =restTemplateService.postRequestToSlack(slackProperties.slackServerHealth(), "Server Health", health);
        log.info(String.valueOf(response));
    }
}
