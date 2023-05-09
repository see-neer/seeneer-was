package com.repill.was.global.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repill.was.global.config.WebClientProperty.WebClientServiceProperty;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Slf4j
public class WebClientFactory {
    private final Map<ServiceDestination, WebClientServiceProperty> servicePropertyMap;
    private final Map<ServiceDestination, WebClient> webClientMap;

    public WebClientFactory(WebClientProperty webClientProperty) {
        this.webClientMap = new HashMap<>();
        this.servicePropertyMap = webClientProperty.getServiceProperties().stream()
                .collect(Collectors.toMap(WebClientServiceProperty::getService, Function.identity()));
    }

    public WebClient get(ServiceDestination serviceDestination) {
        WebClient webClient = webClientMap.get(serviceDestination);

        if (webClient != null) {
            return webClient;
        } else {
            webClient = newWebClient(serviceDestination);
            webClientMap.put(serviceDestination, webClient);
            return webClient;
        }
    }

    public WebClientServiceProperty getProperty(ServiceDestination host) {
        return servicePropertyMap.get(host);
    }

    private WebClient newWebClient(ServiceDestination serviceDestination) {
        WebClientServiceProperty property = servicePropertyMap.get(serviceDestination);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Jackson2JsonDecoder decoder = new Jackson2JsonDecoder(mapper);
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().maxInMemorySize(-1);
                    configurer.defaultCodecs().jackson2JsonDecoder(decoder);
                })
                .build();
        exchangeStrategies
                .messageWriters().stream()
                .filter(LoggingCodecSupport.class::isInstance)
                .forEach(writer -> ((LoggingCodecSupport)writer).setEnableLoggingRequestDetails(true));

        ConnectionProvider connectionProvider = ConnectionProvider.builder(serviceDestination.name())
                .maxConnections(property.getMaxConnections())
                .pendingAcquireTimeout(Duration.ofMillis(property.getPendingAcquireTimeout()))
                .pendingAcquireMaxCount(property.getPendingAcquireMaxCount())
                .maxIdleTime(Duration.ofMillis(property.getMaxIdleTime()))
                .build();

        return WebClient.builder()
                .baseUrl(property.getBaseUrl())
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient
                                    .create(connectionProvider)
                                    .followRedirect(true)
                                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                                    .doOnConnected(conn ->
                                            conn.addHandlerLast(new ReadTimeoutHandler(property.getReadTimeout(), TimeUnit.MILLISECONDS))
                                                .addHandlerLast(new WriteTimeoutHandler(property.getWriteTimeout(), TimeUnit.MILLISECONDS))
                                    )
                        )
                )
                .exchangeStrategies(exchangeStrategies)
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                        clientRequest -> {
                            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
                            return Mono.just(clientRequest);
                        }
                ))
                .filter(ExchangeFilterFunction.ofResponseProcessor(
                        clientResponse -> {
                            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
                            return Mono.just(clientResponse);
                        }
                ))
                .build();

    }

    public enum ServiceDestination {
<<<<<<< Updated upstream
        PUBLIC_DATA, SLACK, KAKAO
=======
        AGGREGATION, SLACK
>>>>>>> Stashed changes
    }
}
