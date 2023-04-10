package com.example.testingworkshop;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.testingworkshop.model.Weather;
import com.example.testingworkshop.model.WeatherResponse;
import com.example.testingworkshop.service.WeatherConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

public class WeatherConverterTest {

    private final WeatherConverter weatherConverter = new WeatherConverter();

    @Test
    void convert_shouldMapWeatherToWeatherResponse() {
        // given
        Weather weather = new Weather()
            .setId(1L)
            .setCity("Moscow")
            .setDate(LocalDate.now())
            .setTemperature(323.3);

        // when
        WeatherResponse response = weatherConverter.convert(weather);

        // then
        assertAll("Assert response fields",
            () -> assertEquals(response.city(), weather.getCity(), "City is wrong"),
            () -> assertEquals(response.temperature(), weather.getTemperature(), "Temperature is wrong")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Moscow", "Saratov", "Kholmsk"})
    void convert_shouldMapWeatherToWeatherResponse(String city) {
        // given
        Weather weather = new Weather()
            .setId(1L)
            .setCity(city)
            .setDate(LocalDate.now())
            .setTemperature(323.3);

        // when
        WeatherResponse response = weatherConverter.convert(weather);

        // then
        assertAll("Assert response fields",
            () -> assertEquals(response.city(), weather.getCity(), "City is wrong"),
            () -> assertEquals(response.temperature(), weather.getTemperature(), "Temperature is wrong")
        );
    }

}
