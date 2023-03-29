package com.example.testingworkshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.testingworkshop.model.Current;
import com.example.testingworkshop.model.WeatherApiResponse;
import com.example.testingworkshop.model.WeatherResponse;
import com.example.testingworkshop.repository.WeatherRepository;
import com.example.testingworkshop.service.WeatherApiService;
import com.example.testingworkshop.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

@SpringBootTest
public class WeatherServiceIntegrationTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherRepository weatherRepository;

    @MockBean
    private WeatherApiService weatherApiService;

    @Test
    void getCurrent_shouldReturnWeatherResponse() {
        // given
        String city = "Moscow";
        var apiResponse = new WeatherApiResponse(new Current(32.1));
        var expectedWeatherResponse = new WeatherResponse(city, apiResponse.current().temperature());

        Mockito.when(weatherApiService.getWeather(city)).thenReturn(apiResponse);

        // when
        WeatherResponse response = weatherService.getCurrent(city);

        // then
        assertAll("Assert response",
            () -> assertThat(response.city()).isEqualTo(city),
            () -> assertThat(response.temperature()).isEqualTo(apiResponse.current().temperature()),
            () -> assertTrue(weatherRepository.existsByCityAndDate(city, LocalDate.now())),
            () -> assertThat(weatherRepository.findByCityAndDate(city, LocalDate.now()).getTemperature())
                .isEqualTo(apiResponse.current().temperature())
        );
    }

}
