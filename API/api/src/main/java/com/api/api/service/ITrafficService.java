package com.api.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.api.dto.TrafficDTO;

@Service
public interface ITrafficService {
        
       List<TrafficDTO> getCarPlates();
         List<TrafficDTO> getCarTypes();
        List<TrafficDTO> getCarColors();
        List<TrafficDTO> getCarBrands();

        void changeCarPlate(TrafficDTO trafficDTO, String carPlate);
        void removeCar(String carPlate);

        
}
