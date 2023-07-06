package com.projekt.organizacijarecepata.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyFileException extends Exception{

    public static Logger logger = LoggerFactory.getLogger(EmptyFileException.class);

    public EmptyFileException(String message) {
        super(message);
        logger.error("File is empty error");
    }
}
