package com.example.testingworkshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient weatherClient(@Value("${weather.url}") String weatherUrl) {
        return WebClient.create(weatherUrl);
    }

}
