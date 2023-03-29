package com.example.testingworkshop.service;

import com.example.testingworkshop.model.WeatherApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WeatherApiService {

    @Value("${weather.apiKey}")
    private String apiKey;
    private final WebClient weatherClient;

    public WeatherApiResponse getWeather(String city) {
        try {
            return weatherClient.get()
                .uri(uriBuilder ->
                    uriBuilder.path("/v1/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", city)
                        .queryParam("api", "no")
                        .build())
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .block();
        } catch (Exception e) {
            throw new RuntimeException("Error while get weather from api", e);
        }
    }
}
