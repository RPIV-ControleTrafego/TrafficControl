package com.traffic.traffic.message;

import org.springframework.kafka.annotation.KafkaListener;
import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.AllTraficDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    private final TrafficService trafficService;

    public KafkaConsumerMessage(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @KafkaListener(topics = "traffic-topic", groupId = "traffic-group")
    public void listenToTrafficMessages(AllTraficDTO trafficInfo) {
        if (trafficInfo != null) {
            LOG.info("Received traffic info: {}", trafficInfo);

            try {
                trafficService.newCarDetails(trafficInfo);
                LOG.info("Traffic info processed successfully.");
            } catch (Exception e) {
                LOG.error("Error processing traffic info: {}", e.getMessage());
            }
        } else {
            LOG.warn("Received null traffic information. Skipping processing.");
        }
    }
}
