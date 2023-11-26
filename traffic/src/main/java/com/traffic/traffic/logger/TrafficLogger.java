package com.traffic.traffic.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrafficLogger {

    private static TrafficLogger instance;
    private Logger logger;

    private TrafficLogger() {
        logger = LoggerFactory.getLogger(TrafficLogger.class);
    }

    public static synchronized TrafficLogger getInstance() {
        if (instance == null) {
            instance = new TrafficLogger();
        }
        return instance;
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message) {
        logger.error(message);
    }

    public void logError(String message, Exception exception) {
        logger.error(message, exception);
    }

    public void logErrorWithCustomMessage(String message, String exceptionMessage) {
        String errorMessage = message + ": " + exceptionMessage;
        logger.error(errorMessage);
    }
}
