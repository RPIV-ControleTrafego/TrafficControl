package com.accident.serviceaccident.Generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.message.KafkaProducerMessage;
import com.accident.serviceaccident.service.AccidentService;

import org.springframework.stereotype.Component;

@Component
public class SendInfo {
   

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @Autowired
    private AccidentService AccidentService;

    @Scheduled(fixedRate = 2000) // 1s = 1000ms
    public void postCarPlate() {
        AccidentGenerator AccidentGenerator = new AccidentGenerator();
        AccidentDTO AccidentDTO = new AccidentDTO();
        AccidentService.mapGeneratortoDTO(AccidentDTO, AccidentGenerator);

        kafkaProducerMessage.sendAccidentMessage(AccidentDTO);
    }
}