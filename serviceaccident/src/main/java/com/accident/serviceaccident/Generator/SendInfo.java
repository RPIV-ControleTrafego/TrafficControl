package com.accident.serviceaccident.Generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.message.KafkaProducerMessage;
import com.accident.serviceaccident.service.AccidentService;

import org.springframework.stereotype.Component;

@Component
public class SendInfo {

    private static final Logger LOG = LoggerFactory.getLogger(SendInfo.class);

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @Autowired
    private AccidentService accidentService;

    @Scheduled(fixedRate = 2000)
    public void postCarAccident() {
        AccidentGenerator accidentGenerator = new AccidentGenerator();
        AccidentDTO accidentDTO = new AccidentDTO();

        // Map the generated data to AccidentDTO
        accidentService.mapGeneratorDTO(accidentGenerator, accidentDTO);

        LOG.info("Sending Accident Message to Kafka: {}", accidentDTO);
        kafkaProducerMessage.sendAccidentMessage(accidentDTO);
    }
}
