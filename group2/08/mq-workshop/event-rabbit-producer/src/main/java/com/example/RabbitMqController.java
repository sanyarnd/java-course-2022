package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rabbit")
@RequiredArgsConstructor
public class RabbitMqController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void sendMessage(@RequestBody RabbitMessage rabbitMessage) {
        rabbitTemplate.convertAndSend("myQueue", rabbitMessage.message());
    }

}
