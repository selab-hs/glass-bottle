package com.service.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("com.service.core.common.properties")
public class SlackPropertiesConfig {
}
