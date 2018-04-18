package com.m3c.as.model;

import java.util.*;

/**
 * The WordStorage class is the model part of the program.
 * It holds the different words used in the file in a HashMap with the word itself as the key and
 * its number of occurrences in the file as the value.
 */
public class WordStorage {

    private Map<String, Integer> wordsMap;

    /**
     * Constructor initializes the HashMap.
     */
    public WordStorage(){
        wordsMap = new HashMap<>();
    }


    /**
     * Adds a single word to the HashMap, or increases its value by 1 if it is already there.
     * @param word word to be added to the HashMap.
     */
    public void addWord(String word){
        word = word.toUpperCase();
        if(hasWord(word)){
            int value = wordsMap.get(word);
            wordsMap.put(word, (value+1));
        } else {
            wordsMap.put(word, 1);
        }
    }

    /**
     * Adds multiple words to the HashMap by dividing the String it receives in the input into
     * an array holding all the individual words in that String.
     * @param words String representing a line from the file.
     */
    public void addWords(String words){
        String[] wordList = words.split("[\\W]+");
        for(String word : wordList){
            addWord(word);
        }
    }

    /**
     * Checks if a word exists in the words map.
     * @param word word we want to check in the map.
     * @return true if the map contains the word, false otherwise.
     */
    public boolean hasWord(String word) {
        word = word.toUpperCase();
        if(wordsMap.containsKey(word)){
            return true;
        } else {
            return false;
        }
    }

    public int getWordsMapSize(){
        return wordsMap.size();
    }

    /**
     * Returns how many times a word has appeared.
     * @param word word we want to check in the map.
     * @return value for that word in the HashMap (0 if word isn't in the HashMap)
     */
    public int getWordCount(String word) {
        word = word.toUpperCase();
        if(wordsMap.containsKey(word)){
            return wordsMap.get(word);
        }
        return 0;
    }


    /**
     * Returns a certain amount of words that appear most often.
     * @param numberOfTopWords The number of words we want to appear in the results.
     * @return array holding the top N words and their values as a single string.
     */
    public String[] getTopNWords(int numberOfTopWords){
        String[] result = new String[numberOfTopWords];
        Object[] temp;
        Comparator comp = HashMap.Entry.comparingByValue().reversed();
        temp = wordsMap.entrySet().stream().sorted(comp).toArray();
        for(int i = 0; i<result.length;i++){
            if(i < temp.length) {
                result[i] = temp[i].toString();
            } else {
                result[i] = "Word number " + i + " not found";
            }
        }
        return result;
    }
}