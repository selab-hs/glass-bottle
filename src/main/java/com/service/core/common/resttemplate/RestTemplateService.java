package com.service.core.common.resttemplate;

import com.service.core.common.properties.SlackProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    private final SlackProperties slackProperties;

    public ResponseEntity<String> postToSlack(String uri, String sender, Object data) {
        HttpEntity<Map<String, Object>> entity = createPostSetting(sender, data, ":love_letter:");

        ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.POST, entity, String.class);

        return response;
    }

    public void postExceptionToSlack(String message) {
        HttpEntity<Map<String, Object>> entity = createPostSetting("EXCEPTION-ALARM", message, ":hot_face:");

        new RestTemplate().exchange(slackProperties.slackError(), HttpMethod.POST, entity, String.class);
    }

    public String getToUri(String uri) {
        return new RestTemplate().getForObject(uri, String.class);
    }

    private HttpEntity<Map<String, Object>> createPostSetting(String sender, Object data, String icon) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("username", sender);
        request.put("text", data);
        request.put("icon_emoji", icon);

        return new HttpEntity<>(request, httpHeaders);
    }
}
