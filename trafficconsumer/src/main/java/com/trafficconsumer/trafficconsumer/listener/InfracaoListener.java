package com.trafficconsumer.trafficconsumer.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InfracaoListener {


     @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


     private final Logger logger = LoggerFactory.getLogger(ConsumerTraffic.class);

    @KafkaListener(topics = "${my-topics.traffic}", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("Infração consumida{}", message));
        System.out.println("Consumindo Infração: " + message);
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send("infracao", msg);
        System.out.println("Mensagem enviada: " + msg);
    }
}
