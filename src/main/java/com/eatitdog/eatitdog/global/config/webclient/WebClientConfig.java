package com.eatitdog.eatitdog.global.config.webclient;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;


@Configuration
public class WebClientConfig {

    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();

    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

    @Bean
    public WebClient webClient() {
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        return WebClient.builder()
                .uriBuilderFactory(factory)
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("http-pool")
                .maxConnections(100)
                .pendingAcquireTimeout(Duration.ZERO)
                .pendingAcquireMaxCount(-1)
                .maxIdleTime(Duration.ofMillis(1000L))
                .build();
    }
}
