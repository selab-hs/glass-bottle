package com.service.core.common.resttemplate.vo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class SlackPostRequest {
    private final HttpHeaders headers;
    private final Map<String, Object> requestVo;

    public SlackPostRequest(String sender, Object data, String icon) {
        this.headers = createHeader();
        this.requestVo = createRequestVo(sender, data, icon);
    }

    private HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return httpHeaders;
    }

    private Map<String, Object> createRequestVo(String sender, Object data, String icon) {
        Map<String, Object> request = new HashMap<>();
        request.put("username", sender);
        request.put("text", data);
        request.put("icon_emoji", icon);

        return request;
    }

    public HttpEntity<Map<String, Object>> toEntity(){
        return new HttpEntity<>(requestVo, headers);
    }
}
