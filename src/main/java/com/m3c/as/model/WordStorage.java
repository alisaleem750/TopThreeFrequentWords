package com.m3c.as.model;

import java.util.*;

public class WordStorage {

    private Map<String, Integer> wordsMap;

    public WordStorage(){
        wordsMap = new HashMap<String, Integer>();
    }

    public void addWord(String word){
        word = word.toUpperCase();
        if(hasWord(word)){
            int value = wordsMap.get(word);
            wordsMap.put(word, (value+1));
        } else {
            wordsMap.put(word, 1);
        }
    }

    public void addWords(String words){
        String[] wordList = words.split("[\\W]+");
        for(String word : wordList){
            addWord(word);
        }
    }

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

    public int getWordCount(String word) {
        word = word.toUpperCase();
        if(wordsMap.containsKey(word)){
            return wordsMap.get(word);
        }
        return 0;
    }



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