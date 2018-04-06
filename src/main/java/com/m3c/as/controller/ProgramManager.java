package com.m3c.as.controller;

import com.m3c.as.model.WordStorage;
import com.m3c.as.view.DisplayManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProgramManager {

    String fileName;
    BufferedReader fileReader;
    String[] result;
    WordStorage wordStorage;
    DisplayManager display;

    public ProgramManager(){
        display = new DisplayManager();
        fileName = "resources/aLargeFile";
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        wordStorage = new WordStorage();
    }

    public void start(){
        try {
            long start = System.currentTimeMillis();
            readFile();
            result = wordStorage.getTopNWords(3);
            long end = System.currentTimeMillis();
            long timeTaken = ((end-start)/1000);
            display.displayTimeTaken(timeTaken);
            display.displayTopNWords(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() throws IOException {
        String line;
        while((line = fileReader.readLine()) != null){
            wordStorage.addWords(line);
        }
    }
}