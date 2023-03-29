package com.example.testingworkshop.service;

import com.example.testingworkshop.model.Weather;
import com.example.testingworkshop.model.WeatherResponse;
import org.springframework.stereotype.Component;

@Component
public class WeatherConverter {

    public WeatherResponse convert(Weather weather) {
        return new WeatherResponse(weather.getCity().equals("Moscow") ? "MOSCOW" : weather.getCity(), weather.getTemperature());
    }

}
