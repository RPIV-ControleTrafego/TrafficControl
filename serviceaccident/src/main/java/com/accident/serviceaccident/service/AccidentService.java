package com.accident.serviceaccident.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accident.serviceaccident.Entity.AccidentEntity;
// import com.accident.serviceaccident.client.AccidentClient;
import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.repository.AccidentRepository;

import ch.qos.logback.classic.Logger;


@Service
public class AccidentService implements IAccidentService{
    Logger logger = (Logger) LoggerFactory.getLogger(AccidentService.class);

    @Autowired
    private AccidentRepository accidentRepository;


    public void newAccidentDetails(AccidentDTO accidentDTO) {
        logger.info("Accident service - Received accident information: {}", accidentDTO);

        try {
            AccidentEntity accidentEntity = mapAccidentDTOToEntity(accidentDTO);
            accidentRepository.save(accidentEntity);
            logger.info("Accident service - Accident information saved successfully: {}", accidentEntity);
        } catch (Exception e) {
            logger.error("Accident service - Error while saving accident information: {}", e.getMessage());
        }
    }


    private AccidentEntity mapAccidentDTOToEntity(AccidentDTO accidentDTO){
        AccidentEntity accidentEntity = new AccidentEntity();
        accidentEntity.setTipo(accidentDTO.getTipo());
        accidentEntity.setSeveridade(accidentDTO.getSeveridade());
        accidentEntity.setData(accidentDTO.getData());
        accidentEntity.setVitimas(accidentDTO.getVitimas());
        accidentEntity.setHora(accidentDTO.getHora());
        
        return accidentEntity;
    }

}
