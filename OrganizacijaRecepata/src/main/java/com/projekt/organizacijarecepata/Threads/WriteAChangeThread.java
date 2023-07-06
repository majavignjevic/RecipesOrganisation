package com.projekt.organizacijarecepata.Threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteAChangeThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(WriteAChangeThread.class);

    private final String change;

    public WriteAChangeThread(String change) {
        this.change = change;
    }

    @Override
    public void run() {
        String filePath = "G:/5 semestar/Java/projekt/OrganizacijaRecepata/doc/changes.bin";

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
             ObjectOutputStream out = new ObjectOutputStream(fileOutputStream) {
                 @Override
                 protected void writeStreamHeader() throws IOException {
                     reset();
                 }
             }) {

            String serializedChange = "¨" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")) + " - " + change + ";";
            out.writeObject(serializedChange);

            logger.info("A change has been made: " + change);
        } catch (IOException e) {
            logger.error("Error occurred while changing data " + change + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
