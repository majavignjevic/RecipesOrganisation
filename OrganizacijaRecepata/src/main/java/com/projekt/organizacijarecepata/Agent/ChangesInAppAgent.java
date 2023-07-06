package com.projekt.organizacijarecepata.Agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Agent class responsible for retrieving changes in the application.
 */
public class ChangesInAppAgent {

    private static final Logger logger = LoggerFactory.getLogger(ChangesInAppAgent.class);
    public static String FILEPATH = "G:/5 semestar/Java/projekt/OrganizacijaRecepata/doc/changes.bin";

    public static List<String> getChanges() {
        List<String> changes = new ArrayList<>();

        File file = new File(FILEPATH);
        if (!file.exists() || file.length() == 0) {
            logger.info("File is empty or doesn't exist: " + FILEPATH);
            return changes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                for (String part : parts) {
                    String trimmedPart = part.trim();
                    if (!trimmedPart.isEmpty()) {
                        int startIndex = trimmedPart.indexOf('¨');
                        if (startIndex != -1 && startIndex < trimmedPart.length() - 1) {
                            String formattedPart = trimmedPart.substring(startIndex + 1).trim();
                            changes.add(formattedPart);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found: " + FILEPATH);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Error occurred while reading from file: " + FILEPATH + ": " + e.getMessage());
            e.printStackTrace();
        }

        return changes;
    }




}
