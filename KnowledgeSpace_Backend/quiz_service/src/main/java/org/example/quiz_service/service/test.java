package org.example.quiz_service.service;

import java.io.BufferedReader;
import java.io.File;

public class test {
    public static void main(String[] args) {
        String path = "quiz_service/src/main/resources/static/images/";

        // get the abc.txt file contents showed
        String fileName = "abc.txt";
        String fullPath = path + fileName;

        File file = new File(fullPath);
        System.out.println(file.exists());

        try(BufferedReader br = new BufferedReader(new java.io.FileReader(fullPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
