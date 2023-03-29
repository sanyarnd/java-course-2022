package com.example.testingworkshop.repository;


import com.example.testingworkshop.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    boolean existsByCityAndDate(String city, LocalDate date);

    Weather findByCityAndDate(String city, LocalDate date);

}
