package com.traffic.traffic.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traffic.traffic.dto.TrafficDto;

@Service
public interface ITrafficService {
        
         TrafficDto getCarByPlate(String carPlate);
         List<String> getCarTypes();
         List<String>  getCarColors();
        List<String> getCarBrands();
        List<TrafficDto> listCarsPlate();
        void changeCarPlate(TrafficDto trafficDto, String carPlate);
        void removeCar(String carPlate);


       String getOwnerNameByPlate(String carPlate);
        String getVehicleOwnerCPF(String carPlate);
        String getCarPlateByCPF(String ownerCpf);
        String getCarPlateByOwnerName(String ownerName);
        String getCarPlateByAdress(String addres);
        String getCarPlateByDate(String date);
        List <TrafficDto> getCarPlateByTime(String time);
        List<String> getCarPlateBySpeed(double speedMax, double speedMin);
        
}