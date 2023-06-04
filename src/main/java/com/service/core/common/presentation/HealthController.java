package com.service.core.common.presentation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class HealthController {
    private final Environment environment;

    @GetMapping(path = {"/", "/health"})
    public HealthResponse health() {
        return new HealthResponse(
                "Health Good",
                Arrays.stream(environment.getActiveProfiles()).findFirst().get(),
                LocalDateTime.now()
        );
    }

    @Data
    @AllArgsConstructor
    private static class HealthResponse {
        private final String message;
        private final String environment;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        private final LocalDateTime dateTime;
    }
}
