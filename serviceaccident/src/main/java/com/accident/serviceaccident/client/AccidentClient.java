// package com.accident.serviceaccident.client;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Objects;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;

// import com.accident.serviceaccident.dto.AccidentDTO;

// @Component
// public class AccidentClient {
    
//     private final String ACCIDENT_SERVICE_URL = "http://localhost:8080/accident";

//      @Autowired
//     RestTemplate restTemplate;

//      public void addAccident(AccidentDTO accidentDTO) {
//         restTemplate.postForEntity(ACCIDENT_SERVICE_URL + "/accident", accidentDTO, AccidentDTO.class);
//     }

//     public void removeAccident(String id) {
//         restTemplate.delete(ACCIDENT_SERVICE_URL + "/accident/" + id);
//     }

//     public void changeAccident(AccidentDTO accidentDTO, String id) {
//         restTemplate.put(ACCIDENT_SERVICE_URL + "/accident/" + id, accidentDTO, AccidentDTO.class);
//     }

//     public AccidentDTO getAccident(String id) {
//         return restTemplate.getForObject(ACCIDENT_SERVICE_URL + "/accident/" + id, AccidentDTO.class);
//     }

//     public List<AccidentDTO> getAccidents() {
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentBySeverity(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/severity", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByDate(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/date", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByHasInjuries(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/hasInjuries", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByHasFatalities(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/hasFatalities", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByAddress(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/address", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByDescription(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/description", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByType(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/type", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

//     public List<AccidentDTO> getAccidentByHasInfraction(){
//         ResponseEntity<AccidentDTO[]> response = restTemplate.getForEntity(ACCIDENT_SERVICE_URL + "/accidents/hasInfraction", AccidentDTO[].class);
//         return Arrays.asList(Objects.requireNonNull(response.getBody()));
//     }

    
    
// }
