package com.api.api.controller;

import java.util.List;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.api.dto.TrafficDTO;
import com.api.api.message.KafkaProducerMessage;
import com.api.api.service.ITrafficService;

@Controller
@RequestMapping("/api/traffic")
public class TrafficController {
    
    @Autowired
    private ITrafficService trafficService;

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(TrafficController.class);

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @PostMapping("/car-plate")
    public ResponseEntity postCarPlate(@RequestBody TrafficDTO carPlate){

        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Plate information: {}", carPlate);

        kafkaProducerMessage.sendTrafficMessage(carPlate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car-plates")
    public ResponseEntity<List<TrafficDTO>> getCarPlates(){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlates());
    }

    @PutMapping("/car-plate/{carPlate}")
    public ResponseEntity changeCarPlate(@RequestBody TrafficDTO carPlate, @PathVariable("carPlate") String carPlateId){
        trafficService.changeCarPlate(carPlate, carPlateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/car-plate/{carPlate}")
    public ResponseEntity deleteCarPlate(@PathVariable("carPlate") String carPlateId){
        trafficService.removeCar(carPlateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/car-type")
    public ResponseEntity postCarType(@RequestBody TrafficDTO carType){

        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Type information: {}", carType);

        kafkaProducerMessage.sendTrafficMessage(carType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car-types")
    public ResponseEntity<List<TrafficDTO>> getCarTypes(){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarTypes());
    }

   

    @DeleteMapping("/car-type/{carType}")
    public ResponseEntity deleteCarType(@PathVariable("carType") String carTypeId){
        trafficService.removeCar(carTypeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/car-color")
    public ResponseEntity postCarColor(@RequestBody TrafficDTO carColor){

        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Color information: {}", carColor);

        kafkaProducerMessage.sendTrafficMessage(carColor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car-colors")
    public ResponseEntity<List<TrafficDTO>> getCarColors(){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarColors());
    }
    


    //  private final Logger LOG = LoggerFactory.getLogger(CarPostController.class);

    // @Autowired
    // private CarPostStoreService carPostStoreService;

    // @Autowired
    // private KafkaProducerMessage kafkaProducerMessage;

    // @PostMapping("/post")
    // public ResponseEntity postCarForSale(@RequestBody CarPostDTO carPostDTO){

    //     LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Post information: {}", carPostDTO);

    //     kafkaProducerMessage.sendMessage(carPostDTO);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    // @GetMapping("/posts")
    // public ResponseEntity<List<CarPostDTO>> getCarSales(){
    //     return ResponseEntity.status(HttpStatus.FOUND).body(carPostStoreService.getCarForSales());
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id){
    //     carPostStoreService.changeCarForSale(carPostDTO,id);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity deleteCarForSale(@PathVariable("id") String id){
    //     carPostStoreService.removeCarForSale(id);
    //     return new ResponseEntity<>(HttpStatus.OK);
}
