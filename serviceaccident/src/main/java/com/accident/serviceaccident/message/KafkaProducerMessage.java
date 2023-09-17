package com.accident.serviceaccident.message;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.accident.serviceaccident.dto.AccidentDTO;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, AccidentDTO> accidentKafkaTemplate;

    private final String ACCIDENT_KAFKA_TOPIC = "accident-topic";

    public void sendAccidentMessage(AccidentDTO accidentDTO){
        accidentKafkaTemplate.send(ACCIDENT_KAFKA_TOPIC, accidentDTO);
    }
    
}