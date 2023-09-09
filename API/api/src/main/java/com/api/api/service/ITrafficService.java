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


        List <TrafficDTO> getVeiculeOwnerName(String carPlate);
        List <TrafficDTO> getVeiculeOwnerCPF(String carPlate);
        List <TrafficDTO> getCarPlateByCPF(String ownerCpf);
        List <TrafficDTO> getCarPlateByOwnerName(String ownerName);
        List <TrafficDTO> getCarPlateByAdress(String addres);
        List <TrafficDTO> getCarPlateByDate(String date);
        List <TrafficDTO> getCarPlateByTime(String time);
        List <TrafficDTO> getCarPlateBySpeed(double speed);
        



        // private String veiculeOwner;
        // private String time;
        // private String date;
        // private String address;
        // private double speed;
        // private int maxSpeed;
        // private String direction; // north, south, east, west
        // private String streetDirection; // north, south, east, west
    

        
}
