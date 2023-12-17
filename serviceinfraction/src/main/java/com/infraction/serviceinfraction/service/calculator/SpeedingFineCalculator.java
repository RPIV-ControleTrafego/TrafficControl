package com.infraction.serviceinfraction.service.calculator;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.logger.LoggerInfraction;
import org.springframework.stereotype.Component;

@Component
public class SpeedingFineCalculator implements FineCalculatorStrategy {

    private static final double SPEEDING_FINE_FACTOR = 1.5;

    private final LoggerInfraction log = new LoggerInfraction(SpeedingFineCalculator.class);

    @Override
    public double calculateFine(InfractionDTO infractionDTO) {
        double speedingFine = Math.max(0, infractionDTO.getSpeed() - infractionDTO.getMaxSpeed());
        speedingFine *= SPEEDING_FINE_FACTOR;

        log.info("Speeding fine calculated: {} for infraction: {}", speedingFine, infractionDTO);
        return speedingFine;
    }
}
