package ru.tinkoff.edu;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PraiserController {

    private static final Random RANDOM = ThreadLocalRandom.current();


    @Autowired
    private AttributeRepository attributeRepository;


    @GetMapping("/")
    public String praise() {
        List<AttributeEntity> attributes = attributeRepository.findAll();
        if (attributes.isEmpty()) {
            return "Тебя никто не любит :(";
        }

        String attribute = attributes.get(RANDOM.nextInt(attributes.size())).getName();
        return String.format("Ты такой %s!", attribute);
    }

}
