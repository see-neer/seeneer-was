package com.repill.was.global.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WebClientProperty.class)
public class WebClientConfig {
    private final WebClientProperty webClientProperty;

    public WebClientConfig(WebClientProperty webClientProperty) {
        this.webClientProperty = webClientProperty;
    }

    @Bean
    public WebClientFactory webClientFactory() {
        return new WebClientFactory(webClientProperty);
    }

}

