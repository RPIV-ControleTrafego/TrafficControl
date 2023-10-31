package com.accident.serviceaccident.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accident.serviceaccident.Entity.AccidentEntity;
// import com.accident.serviceaccident.client.AccidentClient;
import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.logger.AccidentLogger;
import com.accident.serviceaccident.repository.AccidentRepository;



@Service
public class AccidentService implements IAccidentService{
    
    private final AccidentLogger logger = new AccidentLogger(AccidentService.class);


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
        
        if(!isAccidentValid(accidentDTO)) {
            return null;
        }
    
        AccidentEntity accidentEntity = new AccidentEntity();
        accidentEntity.setTipo(accidentDTO.getTipo());
        accidentEntity.setSeveridade(accidentDTO.getSeveridade());
        accidentEntity.setData(accidentDTO.getData());
        accidentEntity.setVitimas(accidentDTO.getVitimas());
        accidentEntity.setHora(accidentDTO.getHora());
        
        return accidentEntity;
    }

    private boolean isAccidentValid(AccidentDTO accidentDTO) {
        if(accidentDTO == null) {
            logger.info("AccidentDTO is null, not saving accident information");
            return false;
        }
       
        if (accidentDTO.getTipo() == null || accidentDTO.getSeveridade() == 0 || accidentDTO.getData() == null) {
            logger.info("AccidentDTO is invalid, not saving accident information");
            return false;
        }
        return true;
    }

}
