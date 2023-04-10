package com.example.testingworkshop.service;

import com.example.testingworkshop.model.Weather;
import com.example.testingworkshop.model.WeatherApiResponse;
import com.example.testingworkshop.model.WeatherResponse;
import com.example.testingworkshop.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherApiService weatherApiService;
    private final WeatherRepository weatherRepository;
    private final WeatherConverter weatherConverter;

    public WeatherResponse getCurrent(String city) {
        LocalDate today = LocalDate.now();
        if (weatherRepository.existsByCityAndDate(city, today)) {
            logger.info("Get temperature from postgres");
            Weather weather = weatherRepository.findByCityAndDate(city, today);
            return weatherConverter.convert(weather);
        }
        logger.info("Get temperature from weather api");
        WeatherApiResponse response = weatherApiService.getWeather(city);
        Weather weather = weatherRepository.save(
            new Weather().setCity(city).setDate(today).setTemperature(response.current().temperature()));
        return new WeatherResponse(weather.getCity(), weather.getTemperature());
    }

}
