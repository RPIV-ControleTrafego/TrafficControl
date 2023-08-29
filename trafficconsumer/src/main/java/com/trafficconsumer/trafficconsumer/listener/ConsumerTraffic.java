package com.trafficconsumer.trafficconsumer.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper; // Importe a classe ObjectMapper
import com.trafficconsumer.trafficconsumer.model.TrafficMessage;

@Service
public class ConsumerTraffic {
    private final Logger logger = LoggerFactory.getLogger(ConsumerTraffic.class);
    private ObjectMapper objectMapper = new ObjectMapper(); // Crie o ObjectMapper apenas uma vez

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private InfracaoListener infracaoListener;


    @KafkaListener(topics = "${my-topics.traffic}", groupId = "traffic")
    public void consume(String message) throws IOException {
        logger.info(String.format("Consumindo mensagem {}", message));
        // deserializar a mensagem
        List<TrafficMessage> trafficMessages = objectMapper.readValue(message, new TypeReference<List<TrafficMessage>>() {});

       
        for (TrafficMessage trafficMessage : trafficMessages) {
            String timestamp = trafficMessage.getTimestamp();
          

            System.out.println("Consumindo mensagem:");
            System.out.println("Timestamp: " + timestamp);
        System.out.println("Tipo de Infração: " + trafficMessage.getViolationType());
        System.out.println("Local: " + trafficMessage.getLocation());
        }
        
           infracaoListener.sendMessage(message);
      
        

    }

   

}
