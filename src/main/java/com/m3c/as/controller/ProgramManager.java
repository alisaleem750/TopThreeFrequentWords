package com.m3c.as.controller;

import com.m3c.as.model.WordStorage;
import com.m3c.as.view.DisplayManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The ProgramManager class is the controller for the program. It passes information to the model and displays
 * the results in the view.
 */
public class ProgramManager {

    String fileName;
    BufferedReader fileReader;
    String[] result;
    WordStorage wordStorage;
    DisplayManager display;

    private static final Logger logger = LogManager.getLogger("myLogger");

    /**
     * Constructor creates a new display, a new instance of wordStorage (model) and creates
     * a new BufferedReader for the file we will be reading from.
     */
    public ProgramManager() {
        display = new DisplayManager();
        fileName = "resources/aLargeFile";
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        wordStorage = new WordStorage();
    }

    /**
     * Starts the program and sends result to the display.
     */
    public void start() {
        try {
            long start = System.currentTimeMillis();
            readFile();
            result = wordStorage.getTopNWords(3);
            long end = System.currentTimeMillis();
            long timeTaken = ((end - start) / 1000);
            logger.info("time taken to run program: " + timeTaken);
            display.displayTimeTaken(timeTaken);
            display.displayTopNWords(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Passes a line from the file to the WordStorage class until the file runs out of lines.
     * @throws IOException Throws an exception if there is no file to read from or wrong path specified.
     */
    public void readFile() throws IOException {
        String line;
        while ((line = fileReader.readLine()) != null) {
            wordStorage.addWords(line);
       }
    }
}