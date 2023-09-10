package com.traffic.traffic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.traffic.traffic.Service.ITrafficService;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.repository.TrafficRepository;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/service/traffic")
@CrossOrigin(origins = "*")
public class trafficController {

    @Autowired
    private ITrafficService trafficService;

   
    @GetMapping("/car-plate/{carPlate}")
    public ResponseEntity<TrafficDto> getCarByPlate(@PathVariable String carPlate) {
        try {
            
            if (carPlate == null || carPlate.isEmpty()) {
                // Retorne um status HTTP 400 (Bad Request) para indicar que a entrada é inválida
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

           
            TrafficDto trafficDto = trafficService.getCarByPlate(carPlate);

            // Verifique se o objeto foi encontrado
            if (trafficDto != null) {
              
                return new ResponseEntity<>(trafficDto, HttpStatus.OK);
            } else {
               
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
          
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    


    @GetMapping("/car-plate/list")
    public ResponseEntity<List<TrafficDto>> listCarsPlate() {
        try {
            
            List<TrafficDto> trafficDtos = trafficService.listCarsPlate();

        
            if (trafficDtos.isEmpty()) {
          
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
             
                return new ResponseEntity<>(trafficDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
          
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/car-plate/owner/{carPlate}")
    public ResponseEntity<String> getOwnerByPlate(@PathVariable String carPlate) {
        try {
           
            if (carPlate == null || carPlate.isEmpty()) {
                // Retorne um status HTTP 400 (Bad Request) para indicar que a entrada é inválida
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    
          
            TrafficDto trafficDto = trafficService.getCarByPlate(carPlate);
    
          
            if (trafficDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    
         
            String ownerName = trafficDto.getVeiculeOwnerName();
            
            
            return new ResponseEntity<>("Owner Name: "+ownerName, HttpStatus.OK);
        } catch (Exception e) {
           
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

  
}

 


  



