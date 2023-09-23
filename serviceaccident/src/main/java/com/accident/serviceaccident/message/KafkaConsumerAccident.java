package com.accident.serviceaccident.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.service.AccidentService;





    @Component
    public class KafkaConsumerAccident {

        private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerAccident.class);

        @Autowired
        private AccidentService accidentService;
        @KafkaListener(topics = "accident-topic", groupId = "accident-topic")
        public void listening(AccidentDTO accidentInfo) {

            LOG.info("Accident service - Received accident info information: {}", accidentInfo);
          accidentService.newAccidentDetails(accidentInfo);

        }

    }



