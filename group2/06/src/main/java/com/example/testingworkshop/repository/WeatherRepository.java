package com.example.testingworkshop.repository;


import com.example.testingworkshop.model.Weather;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Query(value = "select count() > 0 from weather w where w.city = :city and w.date = :date")
    boolean existsByCityAndDate(@Param("city") String city, @Param("date") LocalDate date);

    Weather findByCityAndDate(String city, LocalDate date);

}
