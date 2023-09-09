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
    public ResponseEntity<TrafficDTO> postCarPlate(@RequestBody TrafficDTO carPlate){

        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Plate information: {}", carPlate);

        kafkaProducerMessage.sendTrafficMessage(carPlate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car-plates")
    public ResponseEntity<List<TrafficDTO>> getCarPlates(){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlates());
    }

    @PutMapping("/car-plate/{carPlate}")
    public ResponseEntity<TrafficDTO> changeCarPlate(@RequestBody TrafficDTO carPlate, @PathVariable("carPlate") String carPlateId){
        trafficService.changeCarPlate(carPlate, carPlateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/car-plate/{carPlate}")
    public ResponseEntity<TrafficDTO> deleteCarPlate(@PathVariable("carPlate") String carPlateId){
        trafficService.removeCar(carPlateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/car-type")
    public ResponseEntity<TrafficDTO> postCarType(@RequestBody TrafficDTO carType){

        LOG.info("USANDO EVENTOS/MENSAGENS KAFKA - Producer Car Type information: {}", carType);

        kafkaProducerMessage.sendTrafficMessage(carType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/car-types")
    public ResponseEntity<List<TrafficDTO>> getCarTypes(){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarTypes());
    }

   

    @DeleteMapping("/car-type/{carType}")
    public ResponseEntity<TrafficDTO> deleteCarType(@PathVariable("carType") String carTypeId){
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

    @GetMapping("/car-brand")
    public ResponseEntity<List<TrafficDTO>> getCarBrands(){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarBrands());
    }


    @GetMapping("/car-plate/{carPlate}/owner-name")
    public ResponseEntity<List<TrafficDTO>> getVeiculeOwnerName(@PathVariable("carPlate") String carPlate){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getVeiculeOwnerName(carPlate));
    }

    @GetMapping("/car-plate/{carPlate}/owner-cpf")
    public ResponseEntity<List<TrafficDTO>> getVeiculeOwnerCPF(@PathVariable("carPlate") String carPlate){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getVeiculeOwnerCPF(carPlate));
    }

    @GetMapping("/owner-cpf/{ownerCpf}/car-plate")
    public ResponseEntity<List<TrafficDTO>> getCarPlateByCPF(@PathVariable("ownerCpf") String ownerCpf){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlateByCPF(ownerCpf));
    }

    @GetMapping("/owner-name/{ownerName}/car-plate")
    public ResponseEntity<List<TrafficDTO>> getCarPlateByOwnerName(@PathVariable("ownerName") String ownerName){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlateByOwnerName(ownerName));
    }

    @GetMapping("/address/{address}/car-plate")
    public ResponseEntity<List<TrafficDTO>> getCarPlateByAdress(@PathVariable("address") String address){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlateByAdress(address));
    }

    @GetMapping("/date/{date}/car-plate")
    public ResponseEntity<List<TrafficDTO>> getCarPlateByDate(@PathVariable("date") String date){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlateByDate(date));
    }

    @GetMapping("/time/{time}/car-plate")
    public ResponseEntity<List<TrafficDTO>> getCarPlateByTime(@PathVariable("time") String time){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlateByTime(time));
    }

    @GetMapping("/speed/{speed}/car-plate")
    public ResponseEntity<List<TrafficDTO>> getCarPlateBySpeed(@PathVariable("speed") double speed){
        return ResponseEntity.status(HttpStatus.FOUND).body(trafficService.getCarPlateBySpeed(speed));
    }


    


}
