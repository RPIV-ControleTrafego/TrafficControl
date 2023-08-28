package com.trafficproducer.trafficproducer.service;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;




@Service
public class InfracaoService {
    
    private static final Logger logger = LoggerFactory.getLogger(InfracaoService.class);

    @Value("${my-topics.infracao}")
    private String topicinfracao;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info("Infração de Transito -> {}", message);
        this.kafkaTemplate.send(topicinfracao, message);
}

}