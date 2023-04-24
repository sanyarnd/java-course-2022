package com.example;

import static com.example.RandomUtils.genRandom;

import com.example.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventProducer {

    private static final String[] EVENT_TYPES = new String[] {"NEW", "UPDATE", "DECLINE"};

    @Value("${kafka.events.topic}")
    private String topic;

    private final KafkaTemplate<Long, Event> kafkaTemplate;

    @Scheduled(fixedRate = 100)
    public void send() {
        Long key = ThreadLocalRandom.current().nextLong();
        Event event = Event.newBuilder()
            .setId(ThreadLocalRandom.current().nextLong())
            .setName(genRandom(15))
            .setType(getRandomType())
            .setDate(Instant.now())
            .build();
        var future = kafkaTemplate.send(topic, key, event);
        future.thenAccept(result ->
            log.info("Successfully send event {} in topic {}, {}", result.getProducerRecord(), topic, result.getRecordMetadata()));
    }

    private String getRandomType() {
        int idx = ThreadLocalRandom.current().nextInt(0, 3);
        return EVENT_TYPES[idx];
    }
}
