package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/exception")
@RestController
public class ExceptionTestController {

    @GetMapping("/handled")
    public void handledException() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/un-handled")
    public void unhandledException() {
        throw new NullPointerException();
    }
}
