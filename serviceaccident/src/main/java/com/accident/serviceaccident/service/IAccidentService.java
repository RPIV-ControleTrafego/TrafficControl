package com.accident.serviceaccident.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accident.serviceaccident.dto.AccidentDTO;

@Service
public interface IAccidentService {
    
     
    private String accidentId;
    private String severity;
    private String date;
    private boolean hasInjuries;
    private boolean hasFatalities;
    private String address;
    private String description;
    private String type;
    private String hasInfraction;

    public List<AccidentDTO> getAllAccidents();
    public AccidentDTO getAccidentById(String accidentId);
  
    public void updateAccident(AccidentDTO accidentDTO, String accidentId);
    public void deleteAccident(String accidentId);
    public List<AccidentDTO> getAccidentsBySeverity(String severity);
    public List<AccidentDTO> getAccidentsByDate(String date);
    public List<AccidentDTO> getAccidentsByHasInjuries(boolean hasInjuries);
    public List<AccidentDTO> getAccidentsByHasFatalities(boolean hasFatalities);
    public List<AccidentDTO> getAccidentsByAddress(String address);
    public List<AccidentDTO> getAccidentsByDescription(String description);
    public List<AccidentDTO> getAccidentsByType(String type);
    public List<AccidentDTO> getAccidentsByHasInfraction(String hasInfraction);
}
