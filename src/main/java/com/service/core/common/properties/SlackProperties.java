package com.service.core.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "logging.slack.uri")
public record SlackProperties(String slackError, String slackGlassBottle
        , String slackJoinMember, String slackServerHealth) {
}
