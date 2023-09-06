package com.trafficconsumer.trafficconsumer.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerTraffic {

    private final Logger logger = LoggerFactory.getLogger(ConsumerTraffic.class);

    @KafkaListener(topics = "${my-topics.traffic}", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("Consumindo mensagem {}", message));
        System.out.println("Consumindo mensagem: " + message);
    }
}