package com.projekt.organizacijarecepata.entiteti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPrinter<T> {
    private static final Logger logger = LoggerFactory.getLogger(LoggerPrinter.class);
    private T loggable;

    public LoggerPrinter(T loggable) {
        this.loggable = loggable;
    }

    public void logInfo(String message) {
        logger.info(loggable.getClass().getSimpleName() + ": " + message);
    }

    public void logError(String message) {
        logger.error(loggable.getClass().getSimpleName() + ": " + message);
    }
}
