package com.traffic.traffic.message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



import org.springframework.kafka.annotation.KafkaListener;

import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;

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

    /**
     * Listens to the "traffic-topic" Kafka topic with a group ID of "traffic-topic".
     * Receives traffic information in the form of a TrafficDto object.
     * Logs the received traffic information using a logger.
     * Calls the newCarDetails() method in the TrafficService class to process the received traffic details.
     *
     * @param trafficInfo the traffic information received
     */
    @KafkaListener(topics = "traffic-topic", groupId = "traffic-topic")
    public void listening(TrafficDto trafficInfo) {
        LOG.info("Traffic service - Received traffic info: {}", trafficInfo);
        trafficService.newCarDetails(trafficInfo);
    }
}
