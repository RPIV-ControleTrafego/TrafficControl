package com.infraction.serviceinfraction.service.calculator;

import com.infraction.serviceinfraction.dto.InfractionDTO;

import java.util.Map;

public class FinePriceCalculator {
    private FineCalculatorStrategy fineCalculatorStrategy;
    private Map<String, Double> finePrices;

    public FinePriceCalculator(FineCalculatorStrategy fineCalculatorStrategy, Map<String, Double> finePrices) {
        this.fineCalculatorStrategy = fineCalculatorStrategy;
        this.finePrices = finePrices;
    }

    public double calculateFine(InfractionDTO infractionDTO) {
        if (infractionDTO == null) {
            throw new IllegalArgumentException("InfractionDTO cannot be null.");
        }

        double finePrice = 0;

        if (infractionDTO.getSpeed() > infractionDTO.getMaxSpeed()) {
            finePrice = fineCalculatorStrategy.calculateFine(infractionDTO);
        }

        if (infractionDTO.getViolation() != null) {
            finePrice += finePrices.getOrDefault(infractionDTO.getViolation().toLowerCase(), 0.0);
        }

        return finePrice;
    }
}
