package com.example.codefirst.demo.configuration;

import com.example.codefirst.demo.client.ContractFirstDemoClient;
import com.example.codefirst.demo.client.DemoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public DemoClient webClient() {
        return ContractFirstDemoClient.create();
    }

}
