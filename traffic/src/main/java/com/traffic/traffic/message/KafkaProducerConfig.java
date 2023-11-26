package com.traffic.traffic.message;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    private Map<String, Object> producerCommonConfig() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return configProps;
    }

    private <T> ProducerFactory<String, T> createProducerFactory(Class<T> valueType) {
        Map<String, Object> configProps = producerCommonConfig();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private <T> KafkaTemplate<String, T> createKafkaTemplate(Class<T> valueType) {
        return new KafkaTemplate<>(createProducerFactory(valueType));
    }

    @Bean
    public KafkaTemplate<String, TrafficDto> trafficKafkaTemplate() {
        return createKafkaTemplate(TrafficDto.class);
    }

    @Bean
    public KafkaTemplate<String, InfractionDTO> infractionKafkaTemplate() {
        return createKafkaTemplate(InfractionDTO.class);
    }

    @Bean
    public KafkaTemplate<String, AccidentDTO> accidentKafkaTemplate() {
        return createKafkaTemplate(AccidentDTO.class);
    }
}
