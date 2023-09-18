package com.infraction.serviceinfraction.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infraction.serviceinfraction.calculator.FinePriceCalculator;
import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.repository.InfractionRepository;

import org.slf4j.Logger;

@Service
public class InfractionService implements IinfractionService{

      private final Logger log = LoggerFactory.getLogger(InfractionService.class);

    @Autowired
    private InfractionRepository infractionRepository;

    @Autowired
    private InfractionEntity infractionEntity;

    @Override
    public void newInfraction(InfractionDTO infractionInfo) {
        log.info("Traffic service - Received traffici info information: {}", infractionInfo);

        try {
            infractionEntity = mapInfractionDTOTInfractionEntity(infractionInfo);
            boolean fineCalculated = calculateFine(infractionInfo);

            if (fineCalculated) {
                log.info("Traffic service - Fine price calculated: {}", infractionInfo.getFinePrice());
            }


            infractionRepository.save(infractionEntity);
            log.info("Traffic service - Traffic info saved successfully");
        } catch (Exception e) {
            log.error("Traffic service - Error saving traffic info: {}", e.getMessage());
    }
    
}


private InfractionEntity mapInfractionDTOTInfractionEntity(InfractionDTO infractionDTO){
    return InfractionEntity.builder()
    .carPlate(infractionDTO.getCarPlate())
    .addres(infractionDTO.getAddres())
    .date(infractionDTO.getDate())
    .violation(infractionDTO.getViolation())
    .carType(infractionDTO.getCarType())
    .carColor(infractionDTO.getCarColor())
    .carBrand(infractionDTO.getCarBrand())
    .veiculeOwnerName(infractionDTO.getVeiculeOwnerName())
    .veiculeOwneCPF(infractionDTO.getVeiculeOwneCPF())
    .speed(infractionDTO.getSpeed())
    .maxSpeed(infractionDTO.getMaxSpeed())
    .build();
}


  private boolean calculateFine(InfractionDTO infractionDTO) {
      
        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation().toLowerCase(), infractionDTO);
        infractionDTO.setFinePrice(finePrice);
        return finePrice > 0;  
    }



 }



