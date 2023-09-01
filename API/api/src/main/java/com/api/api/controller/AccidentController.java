package com.api.api.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.api.dto.AccidentDTO;
import com.api.api.message.KafkaProducerMessage;

import com.api.api.service.IAccidentService;

@Controller
@RequestMapping("/api/accident")
public class AccidentController {
    
    @Autowired
    private IAccidentService accidentService;

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(AccidentController.class);
    
    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @PostMapping("/accidentpost")
    public ResponseEntity postAccident(@RequestBody AccidentDTO accident){
        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Accident information: {}", accident);
        kafkaProducerMessage.sendAccidentMessage(accident);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    

}
