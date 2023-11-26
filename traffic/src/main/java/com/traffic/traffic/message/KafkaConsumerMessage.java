package com.traffic.traffic.message;

import org.springframework.kafka.annotation.KafkaListener;
import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.AllTraficDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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