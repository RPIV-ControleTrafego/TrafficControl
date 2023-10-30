package com.traffic.traffic.message;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.dto.AccidentDTO;



@Component
public class KafkaProducerMessage {
    private final Logger LOG = LoggerFactory.getLogger(KafkaProducerMessage.class);
   
    @Autowired
    private KafkaTemplate<String, InfractionDTO> kafkaTemplate;

    @Autowired
    private  KafkaTemplate<String, AccidentDTO> kafkaAccidentTemplate;

    private final String KAFKA_TOPIC = "infraction-topic";

    public void sendMessage(InfractionDTO infractionDTO){
        LOG.info("Infraction service - Sending infraction information: {}", infractionDTO);
        kafkaTemplate.send(KAFKA_TOPIC, infractionDTO);
    }

    public void sendAccidentMessage(AccidentDTO accidentDTO){
        LOG.info("Accident service - Sending accident information: {}", accidentDTO);
        kafkaAccidentTemplate.send(KAFKA_TOPIC, accidentDTO);
    }

    
    
    
}