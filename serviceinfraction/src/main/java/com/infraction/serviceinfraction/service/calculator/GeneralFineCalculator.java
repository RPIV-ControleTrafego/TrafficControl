package com.infraction.serviceinfraction.service.calculator;

import org.springframework.stereotype.Component;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.logger.LoggerInfraction;

@Component
public class GeneralFineCalculator implements FineCalculatorStrategy {

    private final LoggerInfraction log = new LoggerInfraction(GeneralFineCalculator.class);

    @Override
    public double calculateFine(InfractionDTO infractionDTO) {
        String violation = infractionDTO.getViolation().toLowerCase();
        double finePrice = FinePrices.getFinePrices().getOrDefault(violation, 0.0);

        log.info("GeneralFineCalculator - Calculating fine for violation: {} with finePrice: {}", violation, finePrice);

        return finePrice;
    }
}
