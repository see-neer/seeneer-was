package com.repill.was.member.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repill.was.global.config.WebClientFactory;
import com.repill.was.global.config.WebClientFactory.ServiceDestination;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.global.shard.response.CommonResponse.ErrorType;
import com.repill.was.member.webclient.dto.TestDto;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Component
public class TestWebClientImpl implements TestWebClient {

    private final WebClientFactory webClientFactory;

    public TestWebClientImpl(WebClientFactory webClientFactory) {
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
                        if(commonResponse1.getErrorType().equals(ErrorType.WARNING)) {
                            return Mono.error(new BadRequestException(commonResponse1.getMessage()));
                        }
                        return Mono.error(new Exception(commonResponse1.getMessage()));
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException(e));
                    }
                }
        );
    }

    @Override
    public TestDto test() throws URISyntaxException {
        URI uri = new URI("http://apis.data.go.kr/1471000/HtfsInfoService03/getHtfsList01?serviceKey=7HDmRbi%2BVlDppa2An2OxtogxqC8ivAcsiGXLTf33m0SUer8Pgt1GoK%2B4qDHyQdcQtbJD1zsQzejns9HEn7RY4w%3D%3D&pageNo=1&numOfRows=10&type=json");
        return webClientFactory
                .get(ServiceDestination.PUBLIC_DATA)
                .method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::mapToClientError)
                .onStatus(HttpStatus::is5xxServerError, this::mapToSeverError)
                .bodyToMono(TestDto.class)
                .block();
    }
}
