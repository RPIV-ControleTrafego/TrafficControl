package com.api.api.client;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.api.api.dto.AccidentDTO;

@Component
public class AccidentClient {
    
    private final String ACCIDENT_SERVICE_URL = "http://localhost:8080/accident";

     @Autowired
    RestTemplate restTemplate;

     public void addAccident(AccidentDTO accidentDTO) {
        restTemplate.postForEntity(ACCIDENT_SERVICE_URL + "/accident", accidentDTO, AccidentDTO.class);
    }

    public void removeAccident(String id) {
        restTemplate.delete(ACCIDENT_SERVICE_URL + "/accident/" + id);
    }

    public void changeAccident(AccidentDTO accidentDTO, String id) {
        restTemplate.put(ACCIDENT_SERVICE_URL + "/accident/" + id, accidentDTO, AccidentDTO.class);
    }

    public AccidentDTO getAccident(String id) {
        return restTemplate.getForObject(ACCIDENT_SERVICE_URL + "/accident/" + id, AccidentDTO.class);
    }

    public List<AccidentDTO> getAccidents() {
        ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents", AccidentDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
    
}
