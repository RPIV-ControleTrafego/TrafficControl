package com.traffic.traffic.logger;

import org.slf4j.Logger;

import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.controller.TrafficController;
import com.traffic.traffic.message.KafkaConsumerMessage;
import com.traffic.traffic.message.KafkaProducerMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrafficLogger {

    private static TrafficLogger instance;
    private Logger logger;

    private TrafficLogger() {
        logger = LoggerFactory.getLogger(TrafficLogger.class); // Obtém um logger usando SLF4J
    }

    public static TrafficLogger getInstance() {
        if (instance == null) {
            instance = new TrafficLogger();
        }
        return instance;
    }

    public void info(String message) {
        logger.info(message); // Registra uma mensagem de informação
    }

    public void error(String message) {
        logger.error(message); // Registra uma mensagem de erro
    }

    public void error(String message, Exception exception) {
        logger.error(message, exception); // Registra uma mensagem de erro com a exceção
    }

    public void error(String message, String exceptionMessage) {
        // Implementação que aceita uma mensagem de erro e uma String de mensagem de exceção
        logger.error(message + ": " + exceptionMessage);
    }

}
