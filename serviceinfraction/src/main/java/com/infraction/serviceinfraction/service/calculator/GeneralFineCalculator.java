package com.infraction.serviceinfraction.service.calculator;

import org.springframework.stereotype.Component;

import com.infraction.serviceinfraction.dto.InfractionDTO;

@Component
public class GeneralFineCalculator implements FineCalculatorStrategy {

    @Override
    public double calculateFine(InfractionDTO infractionDTO) {
        String violation = infractionDTO.getViolation().toLowerCase();
        return FinePrices.getFinePrices().getOrDefault(violation, 0.0);
    }
}
