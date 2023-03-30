package com.example.codefirst.demo.configuration;

import com.example.codefirst.demo.client.ContractFirstDemoClient;
import com.example.codefirst.demo.client.DemoClient;
import com.example.codefirst.demo.client.TestDemoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public DemoClient webClient() {
        return new TestDemoClient();
    }

}