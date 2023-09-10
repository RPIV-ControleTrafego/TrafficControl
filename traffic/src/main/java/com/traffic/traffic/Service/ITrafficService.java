package com.traffic.traffic.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traffic.traffic.dto.TrafficDto;

@Service
public interface ITrafficService {
        
         TrafficDto getCarByPlate(String carPlate);
         void getCarTypes(TrafficDto trafficDto);
         void   getCarColors(TrafficDto trafficDto);
        void getCarBrands(TrafficDto trafficDto);

        void changeCarPlate(TrafficDto trafficDto, String carPlate);
        void removeCar(String carPlate);


        List <TrafficDto> getVeiculeOwnerName(String carPlate);
        List <TrafficDto> getVeiculeOwnerCPF(String carPlate);
        List <TrafficDto> getCarPlateByCPF(String ownerCpf);
        List <TrafficDto> getCarPlateByOwnerName(String ownerName);
        List <TrafficDto> getCarPlateByAdress(String addres);
        List <TrafficDto> getCarPlateByDate(String date);
        List <TrafficDto> getCarPlateByTime(String time);
        List <TrafficDto> getCarPlateBySpeed(double speed);
        
}