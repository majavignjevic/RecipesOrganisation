package com.projekt.organizacijarecepata.Exceptions;

import javafx.application.Platform;

public class CantReadUsersException  extends RuntimeException{
    public CantReadUsersException(String message) {
        super(message);
        Platform.exit();
    }
}
