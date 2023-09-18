package com.infraction.serviceinfraction.calculator;

import java.util.HashMap;
import java.util.Map;

import com.infraction.serviceinfraction.dto.InfractionDTO;

public class FinePriceCalculator {
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
    }

    public static double calculateFinePrice(String violation, InfractionDTO infractionDTO) {
        double finePrice = 0;
                if(infractionDTO.getSpeed() > infractionDTO.getMaxSpeed()){ 
                    finePrice = calculateSpeedingFine(infractionDTO);
                }
        return finePrice; 
    }

    private static double calculateSpeedingFine(InfractionDTO infractionDTO) {
        return (infractionDTO.getSpeed() - infractionDTO.getMaxSpeed()) * finePrices.get("speeding");
    }
}
