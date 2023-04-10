package com.example.testingworkshop.controller;

import com.example.testingworkshop.model.WeatherResponse;
import com.example.testingworkshop.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public WeatherResponse getCurrent(@RequestParam("city") String city) {
        return weatherService.getCurrent(city);
    }

}
