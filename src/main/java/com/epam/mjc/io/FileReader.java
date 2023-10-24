package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    data.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // Throw a custom exception with a meaningful error message
            throw new FileParsingException("Error reading file: " + file.getAbsolutePath(), e);
        }

        // Parse data and create a Profile object
        String name = data.get("Name");
        int age;
        try {
            age = Integer.parseInt(data.get("Age"));
        } catch (NumberFormatException e) {
            // Handle parsing errors and throw a custom exception
            throw new FileParsingException("Error parsing age in the file: " + file.getAbsolutePath(), e);
        }
        String email = data.get("Email");
        long phone;
        try {
            phone = Long.parseLong(data.get("Phone"));
        } catch (NumberFormatException e) {
            // Handle parsing errors and throw a custom exception
            throw new FileParsingException("Error parsing phone number in the file: " + file.getAbsolutePath(), e);
        }

        return new Profile(name, age, email, phone);
    }
}