package com.repill.was.member.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.repill.was.global.config.WebClientFactory;
import com.repill.was.global.config.WebClientFactory.ServiceDestination;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.global.shard.response.CommonResponse.ErrorType;
import com.repill.was.member.webclient.dto.TestDto;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Consumer;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import java.net.URLDecoder;

@Component
public class TestWebClientImpl implements TestWebClient {

    private final WebClientFactory webClientFactory;

    public TestWebClientImpl(WebClientFactory webClientFactory) {
        this.webClientFactory = webClientFactory;
    }

    private Consumer<HttpHeaders> defaultHeadersConsumer() {
        return new Consumer<>() {
            @Override
            public void accept(HttpHeaders httpHeaders) {
                httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8));
            }
        };
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
    public String test() throws URISyntaxException, ParseException, JsonProcessingException {
        URI uri = new URI(
                "http://apis.data.go.kr/1471000/HtfsInfoService03/getHtfsList01?serviceKey=7HDmRbi%2BVlDppa2An2OxtogxqC8ivAcsiGXLTf33m0SUer8Pgt1GoK%2B4qDHyQdcQtbJD1zsQzejns9HEn7RY4w%3D%3D&pageNo=1&numOfRows=10&type=json");
        String block = webClientFactory
                .get(ServiceDestination.PUBLIC_DATA)
                .method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::mapToClientError)
                .onStatus(HttpStatus::is5xxServerError, this::mapToSeverError)
                .bodyToMono(String.class)
                .block();

        JSONObject json = null;
        try {
            json = new JSONObject(block);
            String resultCode = json.getJSONObject("header").getString("resultCode");
            String resultMsg = json.getJSONObject("header").getString("resultMsg");
            int pageNo = json.getJSONObject("body").getInt("pageNo");
            int totalCount = json.getJSONObject("body").getInt("totalCount");
            int numOfRows = json.getJSONObject("body").getInt("numOfRows");
            JSONArray items = json.getJSONObject("body").getJSONArray("items");

            System.out.println("Result code: " + resultCode);
            System.out.println("Result message: " + resultMsg);
            System.out.println("Page number: " + pageNo);
            System.out.println("Total count: " + totalCount);
            System.out.println("Number of rows: " + numOfRows);
            System.out.println("Items: " + items);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return "null";
    }
}
