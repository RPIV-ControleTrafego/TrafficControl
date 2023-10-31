package com.accident.serviceaccident.logger;

import org.slf4j.Logger;

import com.accident.serviceaccident.Entity.AccidentEntity;
import com.accident.serviceaccident.dto.AccidentDTO;

public class AccidentLogger {

    private static AccidentLogger instance;
    private Logger logger;

    private AccidentLogger() {
        
    }

    public static AccidentLogger getInstance() {
        if (instance == null) {
            instance = new AccidentLogger();
        }
        return instance;
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


// package com.infraction.serviceinfraction.logger;

// import org.slf4j.Logger;

// import com.infraction.serviceinfraction.dto.InfractionDTO;
// import com.infraction.serviceinfraction.entity.InfractionEntity;

// public class LoggerInfraction {

//     private static LoggerInfraction instance;
//     private Logger logger;

//     private LoggerInfraction() {
        
//     }

//     public static LoggerInfraction getInstance() {
//         if (instance == null) {
//             instance = new LoggerInfraction();
//         }
//         return instance;
//     }

//     public void info(String message) {
//         logger.info(message); 
//     }

//     public void error(String message) {
//         logger.error(message);
//     }

//     public void error(String message, Exception exception) {
//         logger.error(message, exception); 
//     }

//     public void error(String message, String exceptionMessage) {
      
//         logger.error(message + ": " + exceptionMessage);
//     }

//     public void info(String string, InfractionDTO infractionInfo) {
//         logger.info(string, infractionInfo);
//     }

//     public void info(String string, double finePrice, String violation) {
//         logger.info(string, finePrice, violation);
//     }

//     public void info(String string, InfractionEntity infractionEntity) {
//         logger.info(string, infractionEntity);
//     }

//     public void info(String string, String date) {
//         logger.info(string, date);
//     }

//     public void info(String string, int speed) {
//         logger.info(string, speed);
//     }

   
    
// }




// package com.traffic.traffic.logger;

// import org.slf4j.Logger;

// import org.slf4j.LoggerFactory;

// public class TrafficLogger {

//     private static TrafficLogger instance;
//     private Logger logger;

//     private TrafficLogger() {
//         logger = LoggerFactory.getLogger(TrafficLogger.class); // Obtém um logger usando SLF4J
//     }

//     public static TrafficLogger getInstance() {
//         if (instance == null) {
//             instance = new TrafficLogger();
//         }
//         return instance;
//     }

//     public void info(String message) {
//         logger.info(message); 
//     }

//     public void error(String message) {
//         logger.error(message);
//     }

//     public void error(String message, Exception exception) {
//         logger.error(message, exception); // Registra uma mensagem de erro com a exceção
//     }

//     public void error(String message, String exceptionMessage) {
//         // Implementação que aceita uma mensagem de erro e uma String de mensagem de exceção
//         logger.error(message + ": " + exceptionMessage);
//     }

// }
