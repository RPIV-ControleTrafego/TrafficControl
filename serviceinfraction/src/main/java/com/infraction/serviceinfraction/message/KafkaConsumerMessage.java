package com.infraction.serviceinfraction.message;

import org.springframework.kafka.annotation.KafkaListener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.service.InfractionService;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    // @Autowired
    // private TrafficService trafficService;

    @Autowired
    private InfractionService infractionService;
    
    @KafkaListener(topics = "traffic-topic", groupId = "traffic-topic")
    public void listening(InfractionDTO infractionInfo) {

        LOG.info("Traffic service - Received traffici nfo information: {}", infractionInfo);
      infractionService.newInfraction(infractionInfo);
      
   
    


    }

}