package com.accident.serviceaccident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.accident.serviceaccident.Entity.AccidentEntity;
import com.accident.serviceaccident.dto.AccidentDTO;
import com.accident.serviceaccident.logger.AccidentLogger;
import com.accident.serviceaccident.repository.AccidentRepository;

@Service
public class AccidentService implements IAccidentService {

    private final AccidentLogger logger = new AccidentLogger(AccidentService.class);

    private final AccidentRepository accidentRepository;

    @Autowired
    public AccidentService(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public void newAccidentDetails(AccidentDTO accidentDTO) {
        if (accidentDTO == null) {
            logger.error("Received null AccidentDTO. Cannot save accident information.");
            return;
        }

        if (!isAccidentValid(accidentDTO)) {
            logger.error("Invalid AccidentDTO received. Not saving accident information: {}");
            return;
        }

        try {
            AccidentEntity accidentEntity = mapAccidentDTOToEntity(accidentDTO);
            accidentRepository.save(accidentEntity);
            logger.info("Accident information saved successfully: {}", accidentEntity);
        } catch (DataAccessException e) {
            logger.error("Error while saving accident information: {}", e.getMessage());
            // Handle the exception as needed
        }
    }

    private AccidentEntity mapAccidentDTOToEntity(AccidentDTO accidentDTO) {
        AccidentEntity accidentEntity = new AccidentEntity();
        accidentEntity.setTipo(accidentDTO.getTipo());
        accidentEntity.setSeveridade(accidentDTO.getSeveridade());
        accidentEntity.setData(accidentDTO.getData());
        accidentEntity.setVitimas(accidentDTO.getVitimas());
        accidentEntity.setHora(accidentDTO.getHora());
        return accidentEntity;
    }

    private boolean isAccidentValid(AccidentDTO accidentDTO) {
        return accidentDTO.getTipo() != null
                && accidentDTO.getSeveridade() > 0
                && accidentDTO.getData() != null;
    }
}
