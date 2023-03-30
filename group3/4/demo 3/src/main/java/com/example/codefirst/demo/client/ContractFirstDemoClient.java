package com.example.codefirst.demo.client;

import com.example.codefirst.demo.annotation.WebClientComponent;
import com.example.codefirst.demo.client.dto.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@RequiredArgsConstructor
public class ContractFirstDemoClient implements DemoClient {

    private static final String API_URL = "http://localhost:8080";

    private final WebClient webClient;

    public static ContractFirstDemoClient create() {
        return create(API_URL);
    }

    private static ContractFirstDemoClient create(String baseUrl) {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();

        return new ContractFirstDemoClient(webClient);
    }

    public Hello getHelloFromClient() {
        String path = "/contract-first/hello";
        Hello response = webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(Hello.class)
                .block();

        Objects.requireNonNull(response);

        return response;
    }

}
