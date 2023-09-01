package com.api.api.message;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.api.api.dto.TrafficDTO;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, TrafficDTO> kafkaTemplate;

    private final String KAFKA_TOPIC = "traffic-topic";

    public void sendMessage(TrafficDTO trafficDTO){
        kafkaTemplate.send(KAFKA_TOPIC, trafficDTO);
    }

}
