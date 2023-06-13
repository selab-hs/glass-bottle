package com.service.core.common.resttemplate;

import com.service.core.common.properties.SlackProperties;
import com.service.core.common.resttemplate.vo.SlackPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class RestTemplateService {

    private final SlackProperties slackProperties;

    public String postRequestToSlack(String uri, String sender, Object data) {
        SlackPostRequest request = new SlackPostRequest(sender, data, ":love_letter:");

        return sendPost(uri, request);
    }

    public void postExceptionToSlack(String message) {
        SlackPostRequest request = new SlackPostRequest("EXCEPTION-ALARM", message, ":hot_face:");

        sendPost(slackProperties.slackError(), request);
    }

    public String getToUri(String uri) {
        return new RestTemplate().getForObject(uri, String.class);
    }

    private String sendPost(String uri, SlackPostRequest request) {
        return String.valueOf(
                new RestTemplate().exchange(uri, HttpMethod.POST, request.toEntity(), String.class).toString()
        );
    }
}
