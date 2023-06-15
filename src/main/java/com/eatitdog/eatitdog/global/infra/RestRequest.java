package com.eatitdog.eatitdog.global.infra;

import com.eatitdog.eatitdog.global.config.webclient.WebClientConfig;
import com.eatitdog.eatitdog.global.exception.global.ExternalAPIException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestRequest {

    private final WebClientConfig webClientConfig;

    public <T> T get(String url, Class<T> responseClass) {
        ResponseEntity<T> response = webClientConfig.webClient().method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .toEntity(responseClass)
                .block();
        if (response == null || response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw ExternalAPIException.EXCEPTION;
        }
        return response.getBody();
    }
}
