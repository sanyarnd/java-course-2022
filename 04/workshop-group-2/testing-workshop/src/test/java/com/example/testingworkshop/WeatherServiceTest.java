package com.example.testingworkshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.testingworkshop.model.Current;
import com.example.testingworkshop.model.WeatherApiResponse;
import com.example.testingworkshop.model.WeatherResponse;
import com.example.testingworkshop.repository.WeatherRepository;
import com.example.testingworkshop.service.WeatherApiService;
import com.example.testingworkshop.service.WeatherConverter;
import com.example.testingworkshop.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherApiService weatherApiService;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherConverter weatherConverter;

    @Test
    void getCurrent_shouldReturnWeatherResponse() {
        // given
        String city = "Moscow";
        var apiResponse = new WeatherApiResponse(new Current(32.1));
        var expectedWeatherResponse = new WeatherResponse(city, apiResponse.current().temperature());

        Mockito.when(weatherRepository.existsByCityAndDate(Mockito.any(), Mockito.any()))
                .thenReturn(false);
        Mockito.when(weatherApiService.getWeather(city)).thenReturn(apiResponse);
        Mockito.when(weatherRepository.save(Mockito.any())).then(t -> t.getArgument(0));

        // when
        WeatherResponse response = weatherService.getCurrent(city);

        // then
        assertThat(response.city()).isEqualTo(city);
        assertThat(response.temperature()).isEqualTo(apiResponse.current().temperature());
    }

}
