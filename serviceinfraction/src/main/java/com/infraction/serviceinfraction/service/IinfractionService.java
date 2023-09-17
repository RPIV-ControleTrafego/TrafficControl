package com.infraction.serviceinfraction.service;

import org.springframework.stereotype.Service;

import com.infraction.serviceinfraction.dto.InfractionDTO;

@Service
public interface IinfractionService {
        void newInfraction(InfractionDTO infractionInfo);
}
