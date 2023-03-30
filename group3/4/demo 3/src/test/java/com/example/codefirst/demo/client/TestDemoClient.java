package com.example.codefirst.demo.client;

import com.example.codefirst.demo.client.dto.Hello;

public class TestDemoClient implements DemoClient {

    @Override
    public Hello getHelloFromClient() {
        return new Hello()
                .setText("Hello from mock!");
    }

}
