package com.eatitdog.eatitdog.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "open-api")
public class OpenAPIProperties {
    private String key;
    private String baseUrl;
}
