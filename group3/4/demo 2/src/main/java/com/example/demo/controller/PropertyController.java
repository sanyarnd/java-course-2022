package com.example.demo.controller;

import com.example.demo.property.TestProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {

    private final TestProperties properties;

    @GetMapping
    public TestProperties getTestProperties() {
        return properties;
    }
}
