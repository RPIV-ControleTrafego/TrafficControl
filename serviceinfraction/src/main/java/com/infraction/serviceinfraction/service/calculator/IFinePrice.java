package com.infraction.serviceinfraction.service.calculator;

import com.infraction.serviceinfraction.dto.InfractionDTO;

public interface IFinePrice {
    
    // Aplica Strategy Pattern
    double calculateFine(InfractionDTO infractionDTO);
}

