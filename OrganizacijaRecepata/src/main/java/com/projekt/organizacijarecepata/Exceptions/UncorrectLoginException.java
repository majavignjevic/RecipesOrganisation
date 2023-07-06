package com.projekt.organizacijarecepata.Exceptions;

import com.projekt.organizacijarecepata.Base.Database;
import com.projekt.organizacijarecepata.Controller.LoginController;
import com.projekt.organizacijarecepata.Controller.WelcomeScreenController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class UncorrectLoginException extends Exception{

    public static Logger logger = LoggerFactory.getLogger(UncorrectLoginException.class);

    public UncorrectLoginException() {
        logger.error("User " + LoginController.user.getUsername() + " has uncorrecty logged in");
    }
    public UncorrectLoginException(String message) {
        super(message);
        logger.error("User " + LoginController.user.getUsername() + " has uncorrecty logged in");
    }

    public UncorrectLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncorrectLoginException(Throwable cause) {
        super(cause);
    }

    public UncorrectLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
