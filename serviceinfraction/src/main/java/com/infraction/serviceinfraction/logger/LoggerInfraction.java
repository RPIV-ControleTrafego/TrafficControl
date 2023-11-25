package com.infraction.serviceinfraction.logger;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.entity.InfractionEntity;

public class LoggerInfraction {

   
    private Logger logger;

     public LoggerInfraction(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

  
    public void info(String message) {
        logger.info(message); 
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Exception exception) {
        logger.error(message, exception); 
    }

    public void error(String message, String exceptionMessage) {
      
        logger.error(message + ": " + exceptionMessage);
    }

    public void info(String string, InfractionDTO infractionInfo) {
        logger.info(string, infractionInfo);
    }

    public void info(String string, String cpf, List<InfractionEntity> infractions) {
        logger.info(string, cpf, infractions);
    }

    public void info(String string, InfractionEntity infractionEntity) {
        logger.info(string, infractionEntity);
    }

    public void info(String string, String date) {
        logger.info(string, date);
    }

    public void info(String string, int speed) {
        logger.info(string, speed);
    }


    public void info(String string, String cpf, Double totalFinePrice) {
    }


    public void info(String string, double finePrice, String violation) {
    }

   
    
}



