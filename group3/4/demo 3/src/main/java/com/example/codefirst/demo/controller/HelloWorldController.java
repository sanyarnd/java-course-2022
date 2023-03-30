package com.example.codefirst.demo.controller;

import com.example.codefirst.demo.client.ContractFirstDemoClient;
import com.example.codefirst.demo.client.DemoClient;
import com.example.codefirst.demo.client.dto.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello-world")
@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    private final DemoClient webClient;

    @GetMapping("/")
    public Hello getHelloWorld() {
        return webClient.getHelloFromClient();
    }

}
