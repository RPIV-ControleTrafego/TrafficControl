package com.infraction.serviceinfraction.service.calculator;

import org.springframework.stereotype.Component;

import com.infraction.serviceinfraction.dto.InfractionDTO;

@Component

public interface FineCalculatorStrategy {
    
    // Aplica Strategy Pattern
    double calculateFine(InfractionDTO infractionDTO);
}

