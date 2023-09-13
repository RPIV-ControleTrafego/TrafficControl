package com.api.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api.Generator.TrafficGenerator;
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

    @Override
    public List<TrafficDTO> getVeiculeOwnerName(String carPlate) {
       return  trafficClient.getVeiculeOwnerName(carPlate);
    }

    @Override
    public List<TrafficDTO> getVeiculeOwnerCPF(String carPlate) {
        return trafficClient.getVeiculeOwnerCPF(carPlate);
    }

    @Override
    public List<TrafficDTO> getCarPlateByCPF(String ownerCpf) {
       return trafficClient.getCarPlateByCPF(ownerCpf);
    }

    @Override
    public List<TrafficDTO> getCarPlateByOwnerName(String ownerName) {
        return trafficClient.getCarPlateByOwnerName(ownerName);
    }

    @Override
    public List<TrafficDTO> getCarPlateByAdress(String addres) {
        return trafficClient.getCarPlateByAdress(addres);
    }

    @Override
    public List<TrafficDTO> getCarPlateByDate(String date) {
        return trafficClient.getCarPlateByDate(date);
    }

    @Override
    public List<TrafficDTO> getCarPlateByTime(String time) {
        return trafficClient.getCarPlateByTime(time);
    }

    @Override
    public List<TrafficDTO> getCarPlateBySpeed(double speed) {
        return trafficClient.getCarPlateBySpeed(speed);
    }

    public void mapGeneratortoDTO(TrafficDTO trafficDTO, TrafficGenerator trafficGenerator) {
        // Extrair os atributos relevantes do TrafficGenerator e atribuí-los ao TrafficDTO
        trafficDTO.setCarPlate(trafficGenerator.getCarPlate());
        trafficDTO.setCarType(trafficGenerator.getCarType());
        trafficDTO.setCarColor(trafficGenerator.getCarColor());
        trafficDTO.setCarBrand(trafficGenerator.getCarBrand());
        trafficDTO.setVeiculeOwnerName(trafficGenerator.getFullName());
        trafficDTO.setTime(trafficGenerator.getTime());
        trafficDTO.setDate(trafficGenerator.getDate());
        trafficDTO.setAddress(trafficGenerator.getAddress());
        trafficDTO.setSpeed(trafficGenerator.getSpeed());
        trafficDTO.setMaxSpeed(trafficGenerator.getMaxSpeed());
        trafficDTO.setDirection(trafficGenerator.getDirection());
        trafficDTO.setStreetDirection(trafficGenerator.getStreetDirection());
        
        // Adicione qualquer outro atributo necessário aqui
    
        // Agora, trafficDTO contém os atributos do TrafficGenerator
    }

    
}
