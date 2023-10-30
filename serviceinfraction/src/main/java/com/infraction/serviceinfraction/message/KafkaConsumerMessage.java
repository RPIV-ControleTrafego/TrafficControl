package com.infraction.serviceinfraction.message;

import org.springframework.kafka.annotation.KafkaListener;



import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.service.InfractionService;

@Component
public class KafkaConsumerMessage {

  private Logger log;

    @Autowired
    private InfractionService infractionService;
    
    @KafkaListener(topics = "infraction-topic", groupId = "infraction-topic")
    public void listening(InfractionDTO infractionInfo) {

        log.info("Traffic service - Received traffici nfo information: {}", infractionInfo);
      infractionService.newInfraction(infractionInfo);
      
   
    


    }

}