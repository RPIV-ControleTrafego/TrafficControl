package com.infraction.serviceinfraction.service.calculator;

import com.infraction.serviceinfraction.dto.InfractionDTO;

public interface FineCalculatorStrategy {
    
    // Aplica Strategy Pattern
    double calculateFine(InfractionDTO infractionDTO);
}

