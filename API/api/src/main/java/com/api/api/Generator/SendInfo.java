package com.api.api.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.api.api.dto.TrafficDTO;
import com.api.api.message.KafkaProducerMessage;
import com.api.api.service.TrafficService;

import org.springframework.stereotype.Component;

@Component
public class SendInfo {
   

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @Autowired
    private TrafficService trafficService;

    @Scheduled(fixedRate = 2000) // 1s = 1000ms
    public void postCarPlate() {
        TrafficGenerator trafficGenerator = new TrafficGenerator();
        TrafficDTO trafficDTO = new TrafficDTO();
        trafficService.mapGeneratortoDTO(trafficDTO, trafficGenerator);

        kafkaProducerMessage.sendTrafficMessage(trafficDTO);
    }
}