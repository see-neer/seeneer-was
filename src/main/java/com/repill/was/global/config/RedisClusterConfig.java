package com.repill.was.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@RequiredArgsConstructor
//@Configuration
//@Profile({"prod"})
public class RedisClusterConfig {
//    private final RedisProperties redisProperties;
//
//    // lettuce
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
//        clusterConfiguration.clusterNode(redisProperties.getHost(), redisProperties.getPort());
//        return new LettuceConnectionFactory(clusterConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
}

