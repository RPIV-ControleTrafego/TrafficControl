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
    public void consume(String message) {
        try {
           
            // deserializar a mensagem
            List<TrafficMessage> trafficMessages = objectMapper.readValue(message, new TypeReference<List<TrafficMessage>>() {});

            for (TrafficMessage trafficMessage : trafficMessages) {
                try {
                    String placaVeiculo = trafficMessage.getPlacaVeiculo();
                    int velocidadeVeiculo = trafficMessage.getVelocidadeVeiculo();
                    

                    int velocidadeVia = trafficMessage.getVelocidadeVia();
                    String tipoVeiculo = trafficMessage.getTipoVeiculo();
                    String data = trafficMessage.getData();
                    logger.info(String.format("Placa: %s, Velocidade do Veículo: %d, Velocidade da Via: %d, Tipo do Veículo: %s, Data: %s", placaVeiculo, velocidadeVeiculo, velocidadeVia, tipoVeiculo, data));
                   
                    if(velocidadeVeiculo > velocidadeVia) {
                        infracaoListener.sendMessage(message);
                    }
                    
                } catch (Exception e) {
                    logger.error("Error processing TrafficMessage:", e);
                }
            }
        } catch (IOException e) {
            logger.error("Error deserializing message:", e);
        }
    }
}
