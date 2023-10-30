package com.infraction.serviceinfraction.message;

import org.springframework.kafka.annotation.KafkaListener;



import com.infraction.serviceinfraction.logger.LoggerInfraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.service.InfractionService;

@Component
public class KafkaConsumerMessage {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InfractionService infractionService;
    
    @KafkaListener(topics = "infraction-topic", groupId = "infraction-topic")
    public void listening(InfractionDTO infractionInfo) {

        log.info("Traffic service - Received traffici nfo information: {}", infractionInfo);
      infractionService.newInfraction(infractionInfo);
      
   
    


    }

}