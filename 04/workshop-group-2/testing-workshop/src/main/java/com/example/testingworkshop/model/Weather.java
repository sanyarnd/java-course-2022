package com.example.testingworkshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class Weather {
    @Id
    private Long id;
    private String city;
    private LocalDate date;
    private double temperature;
}
