package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DictionaryTest {
    private Dictionary dictionary = null;

    @Before
    public void setUp() {
        dictionary = new Dictionary();
    }

    @Test
    public void newDictionaryIsEmpty() {
        assertEquals(0, dictionary.size());
    }

    @Test
    public void loadWordsIncrementsSize() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertEquals(5, dictionary.size());
    }

    @Test
    public void loadedWordsAreFound() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertTrue(dictionary.lookup("bear").wasFound);
        assertTrue(dictionary.lookup("bare").wasFound);
        assertTrue(dictionary.lookup("bar").wasFound);
        assertTrue(dictionary.lookup("fear").wasFound);
        assertTrue(dictionary.lookup("dear").wasFound);
    }

    @Test
    public void unloadedWordsAreNotFound() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertFalse(dictionary.lookup("dog").wasFound);
        assertFalse(dictionary.lookup("cat").wasFound);
        assertFalse(dictionary.lookup("fat").wasFound);
        assertFalse(dictionary.lookup("the").wasFound);
        assertFalse(dictionary.lookup("through").wasFound);
    }

    @Test
    public void loadIsAdditive() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        input = new BufferedReader(new FileReader("test/data/tiny_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertTrue(dictionary.lookup("bear").wasFound);
        assertTrue(dictionary.lookup("bare").wasFound);
        assertTrue(dictionary.lookup("bar").wasFound);
        assertTrue(dictionary.lookup("fear").wasFound);
        assertTrue(dictionary.lookup("dear").wasFound);
        assertTrue(dictionary.lookup("dog").wasFound);
        assertTrue(dictionary.lookup("cat").wasFound);
    }

    @Test
    public void dictionaryKnowsAboutsPrefixes() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertFalse(dictionary.lookup("bear").isPrefix);
        assertTrue(dictionary.lookup("bea").isPrefix);
        assertTrue(dictionary.lookup("bar").isPrefix);
        assertFalse(dictionary.lookup("c").isPrefix);
    }
}