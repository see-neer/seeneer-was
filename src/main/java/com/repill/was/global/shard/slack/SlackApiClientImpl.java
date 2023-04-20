package com.repill.was.global.shard.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repill.was.global.config.WebClientFactory;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Component
public class SlackApiClientImpl {

    private final WebClientFactory webClientFactory;

    public SlackApiClientImpl(WebClientFactory webClientFactory) {
        this.webClientFactory = webClientFactory;
    }

    private Mono<Error> mapToClientError(ClientResponse response) {
        return response.bodyToMono(String.class).flatMap(error -> Mono.error(new BadRequestException(error)));
    }

    private Mono<Error> mapToSeverError(ClientResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        return response.bodyToMono(String.class).flatMap(error -> {
                    try {
                        CommonResponse commonResponse1 = mapper.readValue(error, CommonResponse.class);
                        if(commonResponse1.getErrorType().equals(CommonResponse.ErrorType.WARNING)) {
                            return Mono.error(new BadRequestException(commonResponse1.getMessage()));
                        }
                        return Mono.error(new Exception(commonResponse1.getMessage()));
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException(e));
                    }
                }
        );
    }

    public void sendErrorMessage(String payload) {
        webClientFactory
                .get(WebClientFactory.ServiceDestination.SLACK)
                .post()
                .uri(uriBuilder -> uriBuilder.path("/services/T05299LLQ1L/B053ZR5LDSS/DB0kcZwHuDnIfGLF8m4M0lfV").build())
                .bodyValue(payload)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::mapToClientError)
                .onStatus(HttpStatus::is5xxServerError, this::mapToSeverError)
                .bodyToMono(String.class)
                .block();
    }
}
