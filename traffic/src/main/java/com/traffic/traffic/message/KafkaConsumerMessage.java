package com.traffic.traffic.message;


import org.springframework.kafka.annotation.KafkaListener;

import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.AllTraficDTO;
import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.dto.Mapper.TrafficMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private TrafficService trafficService;

    
    @KafkaListener(topics = "traffic-topic", groupId = "traffic-topic")
    public void listening(AllTraficDTO trafficInfo) {
        LOG.info("Traffic service - Received traffic info: {}", trafficInfo);

    
        trafficService.newCarDetails(trafficInfo);
    }
}