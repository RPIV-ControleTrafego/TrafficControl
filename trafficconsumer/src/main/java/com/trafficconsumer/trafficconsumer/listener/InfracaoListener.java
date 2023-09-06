package com.trafficconsumer.trafficconsumer.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InfracaoListener {
    

     private final Logger logger = LoggerFactory.getLogger(ConsumerTraffic.class);

    @KafkaListener(topics = "${my-topics.infracao}", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("Infração consumida{}", message));
        System.out.println("Consumindo Infração: " + message);
    }
}
