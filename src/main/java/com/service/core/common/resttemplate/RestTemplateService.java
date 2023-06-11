package com.service.core.common.resttemplate;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateService {

    public ResponseEntity<String> postToSlack(String uri, String sender, Object data) {
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

    public String getToUri(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }
}
