package com.service.core.slack.presentation;

import com.service.core.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SlackSchedulerService {
    private final MemberService memberService;

    @Value("${logging.slack.uri.slack-glass-bottle}")
    private String letterUri;

    @Value("${logging.slack.uri.slack-join-member}")
    private String memberUri;


//    @Scheduled(cron = "0 0 8 * * *")
//    private void schedulerYesterdayLetter() {
//        로직 생성 이후 추가 예정
//
//    }

    @Scheduled(cron = "0 */1 * * * *")
    private void schedulerYesterdayJoinMember() {
        var response = postToSlack(memberUri, "Slack Message", memberService.getYesterdayJoinUsers());
        log.info(response.toString());

    }

    private ResponseEntity<String> postToSlack(String uri, String sender, Object data) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("username", sender);
        request.put("text", data);
        request.put("icon_emoji", ":love_letter:");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return response;
    }

}
