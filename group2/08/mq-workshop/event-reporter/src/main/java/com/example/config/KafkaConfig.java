package com.example.config;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static io.confluent.kafka.serializers.KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

import com.example.event.Event;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${kafka.schema-registry.url}")
    private String schemaRegistryUrl;
    @Value("${kafka.events.group-id}")
    private String groupId;

    @Bean("events")
    public ConcurrentKafkaListenerContainerFactory<Long, Event> eventKafkaContainerFactory() {
        var consumerFactory =  new DefaultKafkaConsumerFactory<>(getConsumerFactoryProperties());
        var listener = new ConcurrentKafkaListenerContainerFactory<Long, Event>();
        listener.setConcurrency(1);
        listener.setConsumerFactory(consumerFactory);
        return listener;
    }

    private Map<String, Object> getConsumerFactoryProperties() {
        Map<String, Object> config = new HashMap<>();
        config.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        config.put(GROUP_ID_CONFIG, groupId);
        config.put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        config.put(VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        config.put(SPECIFIC_AVRO_READER_CONFIG, "true");
        config.put(FETCH_MAX_WAIT_MS_CONFIG, 3000);
        return config;
    }
}
