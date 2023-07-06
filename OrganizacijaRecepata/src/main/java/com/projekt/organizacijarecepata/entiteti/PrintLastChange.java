package com.projekt.organizacijarecepata.entiteti;

import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrintLastChange<T extends String, U extends LocalDateTime> implements PrintableInterface{
    private T firstParameter;
    private U secondParameter;

    public PrintLastChange(T firstParameter, U secondParameter) {
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
    }

    @Override
    public void printParameters() {
        System.out.println(secondParameter.format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"))
                + " - Latest change was made at"
                + " - " + firstParameter);
    }
}
