package com.traffic.traffic.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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


 @GetMapping("/car/types")
public ResponseEntity<List<String>> getCarTypes() {
    try {
        List<String> carTypeStrings = trafficService.getCarTypes();
        List<String> carTypes = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        // Faz um parse no JSON para extrair o tipo de carro
        for (String carTypeString : carTypeStrings) {
            JsonNode carTypeNode = objectMapper.readTree(carTypeString);
            if (carTypeNode.has("carType")) {
                String carType = carTypeNode.get("carType").asText();
                carTypes.add(carType);
            }
        }

        // Use a Set to ensure uniqueness
        Set<String> uniqueCarTypes = new HashSet<>(carTypes);

        if (uniqueCarTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            
            return new ResponseEntity<>(new ArrayList<>(uniqueCarTypes), HttpStatus.OK);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



@GetMapping("/car/colors")
public ResponseEntity<List<String>> getCarColors() {
    try {
        List<String> carColorStrings = trafficService.getCarColors();
        List<String> carColors = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON strings and extract the "carColor" field
        for (String carColorString : carColorStrings) {
            JsonNode carColorNode = objectMapper.readTree(carColorString);
            if (carColorNode.has("carColor")) {
                String carColor = carColorNode.get("carColor").asText();
                carColors.add(carColor);
            }
        }

        // Use a Set to ensure uniqueness
        Set<String> uniqueCarColors = new HashSet<>(carColors);

        if (uniqueCarColors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ArrayList<>(uniqueCarColors), HttpStatus.OK);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@GetMapping("/car/brands")
public ResponseEntity<List<String>> getCarBrands() {
    try {
        List<String> carBrandStrings = trafficService.getCarBrands();
        List<String> carBrands = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON strings and extract the "carBrand" field
        for (String carBrandString : carBrandStrings) {
            JsonNode carBrandNode = objectMapper.readTree(carBrandString);
            if (carBrandNode.has("carBrand")) {
                String carBrand = carBrandNode.get("carBrand").asText();
                carBrands.add(carBrand);
            }
        }

        // Use a Set to ensure uniqueness
        Set<String> uniqueCarBrands = new HashSet<>(carBrands);

        if (uniqueCarBrands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ArrayList<>(uniqueCarBrands), HttpStatus.OK);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

  
}

 


  



