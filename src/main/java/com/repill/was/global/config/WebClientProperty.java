package com.repill.was.global.config;

import com.repill.was.global.config.WebClientFactory.ServiceDestination;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "webclient")
public class WebClientProperty {
    private List<WebClientServiceProperty> serviceProperties;

    @Getter
    @Setter
    public static class WebClientServiceProperty {
        private ServiceDestination service;
        private String baseUrl;
        private int readTimeout;
        private int writeTimeout;
        private int connectionTimeout;
        private int maxConnections;
        private int pendingAcquireMaxCount;
        private int pendingAcquireTimeout;
        private int maxIdleTime;
    }
}

