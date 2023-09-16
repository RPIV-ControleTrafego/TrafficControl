package com.traffic.traffic.message;


import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;

import java.util.HashMap;
import java.util.Map;



@Component
@Configuration
public class KafkaProducerConfig {
    
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public ProducerFactory<String, TrafficDto> trafficProducerFactory(){

        Map<String,Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        return new DefaultKafkaProducerFactory<>(configProps);
    }

   
  

     @Bean
    public ProducerFactory<String, InfractionDTO> infractionProducerFactory(){

        Map<String,Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        return new DefaultKafkaProducerFactory<>(configProps);
    }

 

    @Bean
    public KafkaTemplate<String, InfractionDTO> infractionKafkaTemplate(){
        return new KafkaTemplate<>(infractionProducerFactory());
    }
}
