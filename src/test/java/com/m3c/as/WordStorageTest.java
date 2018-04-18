package com.m3c.as;

import com.m3c.as.model.WordStorage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class to test the WordStorage class.
 */
public class WordStorageTest {

    WordStorage wordStorage;

    @Before
    public void setup(){
        wordStorage = new WordStorage();
    }

    /**
     * Tests if a single word is added correctly.
     */
    @Test
    public void addWordTest() {
        String test = "hello";
        assertFalse(wordStorage.hasWord(test));
        wordStorage.addWord(test);
        assertTrue(wordStorage.hasWord(test));
    }

    /**
     * Tests if multiple words are added correctly.
     */
    @Test
    public void addWordsTest(){
        String testList = "hi, hello, hey, goodbye";

        assertEquals(0, wordStorage.getWordsMapSize());
        wordStorage.addWords(testList);
        assertEquals(4, wordStorage.getWordsMapSize());
    }

    /**
     * Tests whether a word is found or not in the wordStorage dictionary (HashMap)
     */
    @Test
    public void hasWordTest(){
        String test = "hello";
        assertFalse(wordStorage.hasWord(test));
        wordStorage.addWord(test);
        assertTrue(wordStorage.hasWord(test));
    }

    /**
     * Tests if the count of words inside the HashMap is returned correctly. (no duplicates)
     */
    @Test
    public void getWordsMapSizeTest(){
        assertEquals(0, wordStorage.getWordsMapSize());
        wordStorage.addWord("hi");
        assertEquals(1, wordStorage.getWordsMapSize());
        wordStorage.addWord("Hi");
        assertEquals(1, wordStorage.getWordsMapSize());
        wordStorage.addWord("hey");
        assertEquals(2, wordStorage.getWordsMapSize());
    }

    /**
     * Tests if the amount of times a word has been entered into the HashMap is being stored correctly.
     */
    @Test
    public void getWordCountTest(){
        assertEquals(0, wordStorage.getWordCount("hi"));
        wordStorage.addWord("hi");
        assertEquals(1, wordStorage.getWordCount("hi"));
        wordStorage.addWord("Hi");
        assertEquals(2, wordStorage.getWordCount("hi"));
        wordStorage.addWord("hI");
        assertEquals(3, wordStorage.getWordCount("hi"));
        wordStorage.addWord("HI");
        assertEquals(4, wordStorage.getWordCount("hi"));
    }

    /**
     * Tests that the topNWords function is returning the 3 words with the highest values in the HashMap.
     */
    @Test
    public void getTopNWordsTest(){
        String words = "hi, HI, hI, Hi, hello, hello, hello, goodbye, goodbye, bye";
        wordStorage.addWords(words);
        String[] result = wordStorage.getTopNWords(3);
        assertEquals("HI=4", result[0]);
        assertEquals("HELLO=3", result[1]);
        assertEquals("GOODBYE=2", result[2]);
    }
}
