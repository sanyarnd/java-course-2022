package com.example;

import com.example.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConsumer {

    @KafkaListener(containerFactory = "events",
        topics = "${kafka.events.topic}",
        groupId = "${kafka.events.group-id}",
        autoStartup = "true")
    public void consume(Event event) {
        log.info("Processed event for send push {}", event);
    }

}
