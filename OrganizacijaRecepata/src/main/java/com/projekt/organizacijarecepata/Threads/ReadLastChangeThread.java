package com.projekt.organizacijarecepata.Threads;

import com.projekt.organizacijarecepata.entiteti.PrintLastChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;

public class ReadLastChangeThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ReadLastChangeThread.class);
    public static String FILEPATH = "G:/5 semestar/Java/projekt/OrganizacijaRecepata/doc/changes.bin";
    private static final int INTERVAL_SECONDS = 10;

    @Override
    public void run() {
        while (true) {
            String filePath = "G:/5 semestar/Java/projekt/OrganizacijaRecepata/doc/changes.bin";
            String lastChange = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length > 0) {
                        String lastPart = parts[parts.length - 1].trim();
                        int startIndex = lastPart.indexOf('¨');
                        if (startIndex != -1 && startIndex < lastPart.length() - 1) {
                            lastChange = lastPart.substring(startIndex + 1).trim();
                        }
                    }
                }
                PrintLastChange<String, LocalDateTime> getLatest = new PrintLastChange<>(lastChange, LocalDateTime.now());
                getLatest.printParameters();
            } catch (FileNotFoundException e) {
                logger.error("File not found: " + FILEPATH);
                e.printStackTrace();
            } catch (IOException e) {
                logger.error("Error occurred while reading from file: " + FILEPATH + ": " + e.getMessage());
                e.printStackTrace();
            }

            try {
                Thread.sleep(INTERVAL_SECONDS * 1000);
            } catch (InterruptedException e) {
                logger.error("Thread sleep interrupted: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
