package com.api.api.message;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.api.api.dto.AccidentDTO;
import com.api.api.dto.TrafficDTO;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, TrafficDTO> trafficKafkaTemplate;

    private final String TRAFFIC_KAFKA_TOPIC = "traffic-topic";

    @Autowired
    private KafkaTemplate<String, AccidentDTO> accidentKafkaTemplate;

    private final String ACCIDENT_KAFKA_TOPIC = "accident-topic";

    public void sendTrafficMessage(TrafficDTO trafficDTO){
        trafficKafkaTemplate.send(TRAFFIC_KAFKA_TOPIC, trafficDTO);
    }

    public void sendAccidentMessage(AccidentDTO accidentDTO){
        accidentKafkaTemplate.send(ACCIDENT_KAFKA_TOPIC, accidentDTO);
    }
}