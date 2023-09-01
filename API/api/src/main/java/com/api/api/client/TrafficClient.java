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

   

    // public void ownerPostsClient(OwnerPostDTO newUser){
    //     restTemplate.postForEntity(USER_STORE_SERVICE_URI, newUser, OwnerPostDTO.class);
    // }

    // public void changeCarForSaleClient(CarPostDTO carPostDTO, String id){
    //     restTemplate.put(POSTS_STORE_SERVICE_URI+"/car/"+id,carPostDTO,CarPostDTO.class);
    // }

    // public void deleteCarForSaleClient(String id){
    //     restTemplate.delete(POSTS_STORE_SERVICE_URI+"/car/"+id);
    // }
}
