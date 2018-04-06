package com.m3c.as.view;

public class DisplayManager {

    public void displayTopNWords(String[] TopNWords){
        for(String element : TopNWords){
            System.out.println(element);
        }
    }

    public void displayTimeTaken(long time){
        System.out.println("Time taken: " + time + " seconds");
    }
}
