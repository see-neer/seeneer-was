package com.repill.was.global.slack;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;

@Component
public class SlackApRestTemplateImpl implements SlackApiClient{

    private final RestTemplate restTemplate;

    public SlackApRestTemplateImpl() {
        this.restTemplate =  new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000)) // connection-timeout
                .setReadTimeout(Duration.ofMillis(5000)) // read-timeout
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .build();
    }


    public void sendErrorMessage(String payload) {
//        webClientFactory
//                .get(WebClientFactory.ServiceDestination.SLACK)
//                .post()
//                .uri(uriBuilder -> uriBuilder.path("/services/T05299LLQ1L/B053ZR5LDSS/DB0kcZwHuDnIfGLF8m4M0lfV").build())
//                .bodyValue(payload)
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, this::mapToClientError)
//                .onStatus(HttpStatus::is5xxServerError, this::mapToSeverError)
//                .bodyToMono(String.class)
//                .block();
    }
}
