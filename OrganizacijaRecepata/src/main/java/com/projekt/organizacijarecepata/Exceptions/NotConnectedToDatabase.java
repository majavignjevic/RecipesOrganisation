package com.projekt.organizacijarecepata.Exceptions;

import com.projekt.organizacijarecepata.entiteti.LoggerPrinter;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class NotConnectedToDatabase extends RuntimeException {
    public NotConnectedToDatabase() {

    }

    public NotConnectedToDatabase(String message) {
        super(message);
        Platform.exit();
    }

    public NotConnectedToDatabase(String s, Exception e) {
        Platform.exit();
        LoggerPrinter<String> loggerPrinter = new LoggerPrinter<>(s);
        loggerPrinter.logError("Can't connect to database, message: " + e.getMessage());
    }
}
