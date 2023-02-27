package ru.tinkoff.edu;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PraiserController {

    private static final Random RANDOM = ThreadLocalRandom.current();

    private static final List<String> ATTRIBUTES = List.of("умный", "весёлый", "красивый", "добрый", "заботливый",
            "начитанный", "няшный", "котя");


    @GetMapping("/")
    public String praise() {
        String attribute = ATTRIBUTES.get(RANDOM.nextInt(ATTRIBUTES.size()));
        return String.format("Ты такой %s!", attribute);
    }

}
