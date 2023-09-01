package com.api.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api.client.TrafficClient;
import com.api.api.dto.TrafficDTO;

@Service
public class TrafficService implements ITrafficService {

    @Autowired
    private TrafficClient trafficClient;


    @Override
    public List<TrafficDTO> getCarPlates() {
       return trafficClient.getCarPlates();
    }

    @Override
    public List<TrafficDTO> getCarTypes() {
        return trafficClient.getCarTypes();
    }

    @Override
    public List<TrafficDTO> getCarColors() {
        return trafficClient.getCarColors();
    }

    @Override
    public List<TrafficDTO> getCarBrands() {
        return trafficClient.getCarBrands();
    }

    @Override
    public void changeCarPlate(TrafficDTO trafficDTO, String carPlate) {
        trafficClient.changeCarPlate(trafficDTO, carPlate);
    }

    @Override
    public void removeCar(String carPlate) {
        trafficClient.removeCar(carPlate);
    }
    
}
