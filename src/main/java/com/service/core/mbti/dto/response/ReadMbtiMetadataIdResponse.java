package com.service.core.mbti.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadMbtiMetadataIdResponse {
    private Long id;
    private String type;
    private String name;
    private String description;
}