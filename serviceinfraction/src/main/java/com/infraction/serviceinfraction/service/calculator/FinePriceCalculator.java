package com.infraction.serviceinfraction.service.calculator;

import java.util.HashMap;
import java.util.Map;

import com.infraction.serviceinfraction.dto.InfractionDTO;

public class FinePriceCalculator implements IFinePrice{
    private static final Map<String, Double> finePrices = new HashMap<>();

    static {
        finePrices.put("red light", 150.0);
        finePrices.put("stop sign", 120.0);
        finePrices.put("no seat belt", 80.0);
        finePrices.put("no helmet", 90.0);
        finePrices.put("no license", 200.0);
        finePrices.put("no insurance", 150.0);
        finePrices.put("no registration", 100.0);
        finePrices.put("drunk driving", 500.0);
        finePrices.put("reckless driving", 400.0);
        finePrices.put("hit and run", 600.0);
        finePrices.put("tailgating", 100.0);
        finePrices.put("jaywalking", 50.0);
        finePrices.put("illegal turn", 80.0);
        finePrices.put("parking violation", 100.0);
        finePrices.put("speeding", 45.0);
        finePrices.put("wrong direction", 25.0);
        finePrices.put("no plate", 300.0);
    }

    @Override
    public double calculateFine(InfractionDTO infractionDTO) {
        if (infractionDTO == null) {
            throw new IllegalArgumentException("InfractionDTO cannot be null.");
        }

        double finePrice = 0;

        if (infractionDTO.getSpeed() > infractionDTO.getMaxSpeed()) {
            finePrice = calculateSpeedingFine(infractionDTO);
        }

        if (infractionDTO.getViolation() != null) {
            finePrice += calculateFinePrice(infractionDTO.getViolation().toLowerCase());
        }

        return finePrice;
    }

    private static double calculateSpeedingFine(InfractionDTO infractionDTO) {
        return Math.max(0, infractionDTO.getSpeed() - infractionDTO.getMaxSpeed()) * finePrices.get("speeding");
    }

    private static double calculateFinePrice(String violation) {
        return finePrices.getOrDefault(violation, 0.0);
    }

}
