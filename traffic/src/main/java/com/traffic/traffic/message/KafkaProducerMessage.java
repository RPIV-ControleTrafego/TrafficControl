package com.traffic.traffic.message;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;



@Component
public class KafkaProducerMessage {
    private final Logger LOG = LoggerFactory.getLogger(KafkaProducerMessage.class);
   
    @Autowired
    private KafkaTemplate<String, InfractionDTO> kafkaTemplate;



    private final String KAFKA_TOPIC = "infraction-topic";

    public void sendMessage(InfractionDTO infractionDTO){
        LOG.info("Infraction service - Sending infraction information: {}", infractionDTO);
        kafkaTemplate.send(KAFKA_TOPIC, infractionDTO);
    }

    
    
}