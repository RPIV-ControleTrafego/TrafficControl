package com.api.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api.Generator.AccidentGenerator;
import com.api.api.Generator.TrafficGenerator;
import com.api.api.client.AccidentClient;
import com.api.api.dto.AccidentDTO;
import com.api.api.dto.TrafficDTO;

@Service
public class AccidentService implements IAccidentService{
    
    @Autowired
    private AccidentClient accidentClient;

    @Override
    public List<AccidentDTO> getAllAccidents() {
        return accidentClient.getAccidents();
    }

    @Override
    public AccidentDTO getAccidentById(String accidentId) {
        return accidentClient.getAccident(accidentId);
    }

  

    @Override
    public void updateAccident(AccidentDTO accidentDTO, String accidentId) {
        accidentClient.changeAccident(accidentDTO, accidentId);
    }

    @Override
    public void deleteAccident(String accidentId) {
       accidentClient.removeAccident(accidentId);
    }

    @Override
    public List<AccidentDTO> getAccidentsBySeverity(String severity) {
       return accidentClient.getAccidentBySeverity();
    }

    @Override
    public List<AccidentDTO> getAccidentsByDate(String date) {
       return accidentClient.getAccidentByDate();
    }

    @Override
    public List<AccidentDTO> getAccidentsByHasInjuries(boolean hasInjuries) {
      return accidentClient.getAccidentByHasInjuries();
    }

    @Override
    public List<AccidentDTO> getAccidentsByHasFatalities(boolean hasFatalities) {
       return accidentClient.getAccidentByHasFatalities();
    }

    @Override
    public List<AccidentDTO> getAccidentsByAddress(String address) {
      return accidentClient.getAccidentByAddress();
    }

    @Override
    public List<AccidentDTO> getAccidentsByDescription(String description) {
         return accidentClient.getAccidentByDescription();
    }

    @Override
    public List<AccidentDTO> getAccidentsByType(String type) {
        return accidentClient.getAccidentByType();
    }

    @Override
    public List<AccidentDTO> getAccidentsByHasInfraction(String hasInfraction) {
        return accidentClient.getAccidentByHasInfraction();
    }

  
    // public void mapGeneratortoDTO(TrafficDTO trafficDTO, TrafficGenerator trafficGenerator) {
    //     // Extrair os atributos relevantes do TrafficGenerator e atribuí-los ao TrafficDTO
    //     trafficDTO.setCarPlate(trafficGenerator.getCarPlate());
    //     trafficDTO.setCarType(trafficGenerator.getCarType());
    //     trafficDTO.setCarColor(trafficGenerator.getCarColor());
    //     trafficDTO.setCarBrand(trafficGenerator.getCarBrand());
    //     trafficDTO.setVeiculeOwnerName(trafficGenerator.getFullName());
    //     trafficDTO.setTime(trafficGenerator.getTime());
    //     trafficDTO.setDate(trafficGenerator.getDate());
    //     trafficDTO.setAddress(trafficGenerator.getAddress());
    //     trafficDTO.setSpeed(trafficGenerator.getSpeed());
    //     trafficDTO.setMaxSpeed(trafficGenerator.getMaxSpeed());
    //     trafficDTO.setDirection(trafficGenerator.getDirection());
    //     trafficDTO.setStreetDirection(trafficGenerator.getStreetDirection());
    //     trafficDTO.setViolation(trafficGenerator.getViolation());
    //     trafficDTO.setVeiculeOwneCPF(trafficGenerator.getVehicleOwnerCPF());
    //     trafficDTO.setPollutionLevel(trafficGenerator.getPollutionLevel());
    //     // Adicione qualquer outro atributo necessário aqui
    
    //     // Agora, trafficDTO contém os atributos do TrafficGenerator
    // }

    public void mapGeneratorToDTO(AccidentDTO accidentDTO, AccidentGenerator accidentGenerator) {
        // Extrair os atributos relevantes do AccidentGenerator e atribuí-los ao AccidentDTO
        accidentDTO.setSeverity(accidentGenerator.getSeverity());
        accidentDTO.setDate(accidentGenerator.getDate());
        accidentDTO.setHasInjuries(accidentGenerator.getHasInjuries());
        accidentDTO.setHasFatalities(accidentGenerator.getHasFatalities());
        accidentDTO.setAddress(accidentGenerator.getAddress());
        accidentDTO.setDescription(accidentGenerator.getDescription());
        accidentDTO.setType(accidentGenerator.getType());
        accidentDTO.setHasInfraction(accidentGenerator.getHasInfraction());
        // Adicione qualquer outro atributo necessário aqui
    
        // Agora, accidentDTO contém os atributos do AccidentGenerator
    }

}
