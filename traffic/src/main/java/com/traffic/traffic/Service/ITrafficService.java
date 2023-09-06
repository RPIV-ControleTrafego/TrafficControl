package com.traffic.traffic.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traffic.traffic.dto.TrafficDto;

@Service
public interface ITrafficService {
        
       List<TrafficDto> getCarPlates();
         List<TrafficDto> getCarTypes();
        List<TrafficDto> getCarColors();
        List<TrafficDto> getCarBrands();

        void changeCarPlate(TrafficDto TrafficDto, String carPlate);
        void removeCar(String carPlate);
}
