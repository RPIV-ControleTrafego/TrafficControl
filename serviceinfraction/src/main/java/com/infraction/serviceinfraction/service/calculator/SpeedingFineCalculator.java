package com.infraction.serviceinfraction.service.calculator;

import com.infraction.serviceinfraction.dto.InfractionDTO;

public class SpeedingFineCalculator implements FineCalculatorStrategy {

    @Override
    public double calculateFine(InfractionDTO infractionDTO) {
        return Math.max(0, infractionDTO.getSpeed() - infractionDTO.getMaxSpeed()) * FinePrices.getFinePrices().get("speeding");
    }
}
