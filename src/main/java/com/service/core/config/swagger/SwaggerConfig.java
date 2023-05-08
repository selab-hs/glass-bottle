package com.service.core.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// SwaggerConfig.java
@OpenAPIDefinition(
    info = @Info(title = "채팅서비스 API 명세서",
        description = "DDD를 기반한",
        version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/api/v1/**"};

        return GroupedOpenApi.builder()
            .group("bottle API v1")
            .pathsToMatch(paths)
            .build();
    }
}

//http://localhost:8080/swagger-ui/index.html#/ 여기로 들어가면됨
