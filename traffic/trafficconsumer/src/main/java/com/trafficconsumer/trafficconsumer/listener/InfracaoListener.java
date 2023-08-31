package com.trafficconsumer.trafficconsumer.listener;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trafficconsumer.trafficconsumer.Repository.InfracaoRepository;
import com.trafficconsumer.trafficconsumer.model.InfracaoModel;

@Service
public class InfracaoListener {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(ConsumerTraffic.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private InfracaoRepository infracaoRepository;

    @KafkaListener(topics = "${my-topics.infracao}", groupId = "group_id")
    public void consume(String message) {
        try {
            logger.info(String.format("Infração consumida: %s", message));

            // Converta a mensagem JSON para uma lista de objetos InfracaoModel
            List<InfracaoModel> infracoes = objectMapper.readValue(message, new TypeReference<List<InfracaoModel>>() {});

            for (InfracaoModel infracaoModel : infracoes) {
              
                System.out.println("Data: " + infracaoModel.getData());
                System.out.println("Velocidade do Veículo: " + infracaoModel.getVelocidadeVeiculo());
                System.out.println("Velocidade da Via: " + infracaoModel.getVelocidadeVia());

                // Salve a infração no repositório
                sendInfracaoToRepository(infracaoModel);
            }
        } catch (IOException e) {
            logger.error("Error while processing and consuming infração message:", e);
        }
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send("infracao", msg);
        consume(msg);
    }

    public void sendInfracaoToRepository(InfracaoModel infracaoModel) {
        try {
            infracaoRepository.save(infracaoModel);
            System.out.println("Infração salva no repositório: " + infracaoModel);
        } catch (Exception e) {
            logger.error("Error while saving infração to repository:", e);
        }
    }
}
