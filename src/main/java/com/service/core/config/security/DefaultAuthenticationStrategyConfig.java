package com.service.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DefaultAuthenticationStrategyConfig {
    @Primary
    @Bean
    public DefaultAuthenticationStrategyConfig defaultStrategyConfig() {
        return new DefaultAuthenticationStrategyConfig();
    }
}