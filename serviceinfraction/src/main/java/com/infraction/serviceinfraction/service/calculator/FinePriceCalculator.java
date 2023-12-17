package com.infraction.serviceinfraction.service.calculator;
import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.logger.LoggerInfraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class FinePriceCalculator {
    private List<FineCalculatorStrategy> fineCalculatorStrategies;
    private final LoggerInfraction log = new LoggerInfraction(FinePriceCalculator.class);
    @Autowired
    public FinePriceCalculator(List<FineCalculatorStrategy> fineCalculatorStrategies) {
        this.fineCalculatorStrategies = fineCalculatorStrategies;
    }
    public double calculateFine(InfractionDTO infractionDTO) {
        if (infractionDTO == null) {
            throw new IllegalArgumentException("InfractionDTO cannot be null.");
        }
        double totalFine = 0;
        for (FineCalculatorStrategy strategy : fineCalculatorStrategies) {
            totalFine += strategy.calculateFine(infractionDTO);
        }
        // Special handling for "wrong direction" violation
        if ("wrong direction".equalsIgnoreCase(infractionDTO.getViolation())) {
            // Add the fine for "wrong direction" without setting it as the violation
            double wrongDirectionFine = FinePrices.getFinePrices().getOrDefault("wrong direction", 0.0);
            totalFine += wrongDirectionFine;
        }
        log.info("Total fine calculated: {} for infraction: {}", totalFine, infractionDTO.toString());
        return totalFine;
    }
}
