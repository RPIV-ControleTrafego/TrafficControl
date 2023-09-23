package com.accident.serviceaccident.message;


import org.springframework.kafka.annotation.KafkaListener;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.service.AccidentService;



@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    // @Autowired
    // private TrafficService trafficService;

    @Autowired
    private AccidentService accidentService;
    
    @KafkaListener(topics = "accident-topic", groupId = "accident-topic")
    public void listening(AccidentDTO infractionInfo) {

        LOG.info("Accident service - Received accident info: {}", infractionInfo);
      accidentService.newAccidentDetails(infractionInfo);
      
   
    


    }

}