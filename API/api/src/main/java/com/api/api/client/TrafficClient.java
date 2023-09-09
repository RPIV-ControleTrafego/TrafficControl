package com.api.api.client;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.api.api.dto.TrafficDTO;

@Component
public class TrafficClient {

    private final String TRAFFIC_SERVICE_URL = "http://localhost:8080/traffic";
   
    // private final String INFRACTION_SERVICE_URL = "http://localhost:8080/infraction";

    @Autowired
    RestTemplate restTemplate;

    public List<TrafficDTO> getCarPlates() {
       ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/car-plates", TrafficDTO[].class);
       return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarTypes() {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/car-types", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarColors() {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/car-colors", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarBrands() {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/car-brands", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public void changeCarPlate(TrafficDTO trafficDTO, String carPlate) {
        restTemplate.put(TRAFFIC_SERVICE_URL + "/car-plate/" + carPlate, trafficDTO, TrafficDTO.class);
    }

    public void removeCar(String carPlate) {
        restTemplate.delete(TRAFFIC_SERVICE_URL + "/car-plate/" + carPlate);
    }

    public List<TrafficDTO> getVeiculeOwnerName(String carPlate) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/car-plate/" + carPlate + "/owner-name", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getVeiculeOwnerCPF(String carPlate) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/car-plate/" + carPlate + "/owner-cpf", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarPlateByCPF(String ownerCpf) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/owner-cpf/" + ownerCpf + "/car-plate", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarPlateByOwnerName(String ownerName) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/owner-name/" + ownerName + "/car-plate", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarPlateByAdress(String addres) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/address/" + addres + "/car-plate", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarPlateByDate(String date) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/date/" + date + "/car-plate", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarPlateByTime(String time) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/time/" + time + "/car-plate", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public List<TrafficDTO> getCarPlateBySpeed(double speed) {
        ResponseEntity<TrafficDTO[]> response = restTemplate.getForEntity(TRAFFIC_SERVICE_URL + "/speed/" + speed + "/car-plate", TrafficDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }



}
