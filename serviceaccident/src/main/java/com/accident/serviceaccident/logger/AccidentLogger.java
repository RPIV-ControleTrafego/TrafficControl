package com.accident.serviceaccident.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.accident.serviceaccident.Entity.AccidentEntity;
import com.accident.serviceaccident.dto.AccidentDTO;

public class AccidentLogger {

    private Logger logger;
    public AccidentLogger(Class<?> clazz) {
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

    public AccidentLogger logger() {
        return null;
    }

    public void info(String string, AccidentEntity accidentEntity) {
        logger.info(string, accidentEntity);
    }

    public void info(String string, AccidentDTO accidentDTO) {
        logger.info(string, accidentDTO);

    }

    public void error (String string, String exceptionMessage) {
        logger.error(string + ": " + exceptionMessage);
    }
    
}
