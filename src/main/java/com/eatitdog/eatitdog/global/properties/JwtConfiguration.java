package com.eatitdog.eatitdog.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt.secret")
public class JwtConfiguration {
    private String accessKey;
    private String refreshKey;
}
