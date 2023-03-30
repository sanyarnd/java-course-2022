package com.example.demo.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("properties.qweqweq")
public record TestProperties(
         String foo,
         String bar,
         Integer baz,
         List<String> somePropertyList,
         Map<String, String> somePropertyMap
) { }
