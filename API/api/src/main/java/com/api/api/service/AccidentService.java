package com.api.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api.Generator.AccidentGenerator;
import com.api.api.client.AccidentClient;
import com.api.api.dto.AccidentDTO;

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

     public void mapGeneratorDTO(AccidentGenerator accidentGenerator, AccidentDTO accidentDTO){

        accidentDTO.setAddress(accidentGenerator.getAddress());
        accidentDTO.setDate(accidentGenerator.getDate());
        accidentDTO.setDescription(accidentGenerator.getDescription());
        accidentDTO.setHasFatalities(accidentGenerator.getHasFatalities());
        accidentDTO.setHasInfraction(accidentGenerator.getHasInfraction());
        accidentDTO.setHasInjuries(accidentGenerator.getHasInjuries());
        accidentDTO.setSeverity(accidentGenerator.getSeverity());
        accidentDTO.setType(accidentGenerator.getType());

    }

    

}
