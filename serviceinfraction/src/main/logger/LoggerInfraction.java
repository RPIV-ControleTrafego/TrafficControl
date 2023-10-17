package com.infraction.serviceinfraction.service.logger;

import org.slf4j.Logger;


import com.infraction.serviceinfraction.service.InfractionService;

public class LoggerInfraction {


    // singleton pattern
    private static LoggerInfraction instance;

    private LoggerInfraction() {
      
    }

    public static LoggerInfraction getInstance() {
        if (instance == null) {
            instance = new LoggerInfraction();
        }
        return instance;
    }

    public static Logger getLogger(Class<InfractionService> clazz) {
        return LoggerInfraction.getLogger(clazz);
    }

    public static Logger getLoggerConsumer(Class<KafkaConsumerMessage> clazz) {
        return LoggerInfraction.getLogger(clazz);
    }
}
