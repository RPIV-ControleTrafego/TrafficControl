package com.api.api.Generator;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.api.api.dto.AccidentDTO;
import com.api.api.dto.TrafficDTO;
import com.api.api.message.KafkaProducerMessage;
import com.api.api.service.AccidentService;
import com.api.api.service.TrafficService;

import org.springframework.stereotype.Component;

@Component
public class SendInfo {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(SendInfo.class);

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @Autowired
    private TrafficService trafficService;

    @Autowired
    private AccidentService accidentService;

    @Scheduled(fixedRate = 2000) // 1s = 1000ms
    public void postCarPlate() {
        TrafficGenerator trafficGenerator = new TrafficGenerator();
        TrafficDTO trafficDTO = new TrafficDTO();
        trafficService.mapGeneratortoDTO(trafficDTO, trafficGenerator);
        log.info("Produzindo e enviando para o kafka: " + trafficDTO);
        kafkaProducerMessage.sendTrafficMessage(trafficDTO);
    }

    @Scheduled(fixedRate = 2000) // 1s = 1000ms
    public void postAccident() {
        AccidentGenerator accidentGenerator = new AccidentGenerator();
        AccidentDTO accidentDTO = new AccidentDTO();
        accidentService.mapGeneratorDTO(accidentGenerator, accidentDTO);

        kafkaProducerMessage.sendAccidentMessage(accidentDTO);
    }
}
