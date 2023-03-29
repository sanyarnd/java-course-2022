package com.example.testingworkshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current(@JsonProperty("temp_c") double temperature) {

}
