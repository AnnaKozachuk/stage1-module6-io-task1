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
            throw new RuntimeException("Error reading file: " + file.getAbsolutePath(), e);
        }

        String name = data.get("Name");
        int age = Integer.parseInt(data.get("Age"));
        String email = data.get("Email");
        long phone = Long.parseLong(data.get("Phone"));

        return new Profile(name, age, email, phone);
    }
}