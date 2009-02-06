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

        assertTrue(dictionary.lookup("bear".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("bare".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("bar".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("fear".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("dear".toUpperCase()).wasFound);
    }

    @Test
    public void unloadedWordsAreNotFound() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertFalse(dictionary.lookup("dog".toUpperCase()).wasFound);
        assertFalse(dictionary.lookup("cat".toUpperCase()).wasFound);
        assertFalse(dictionary.lookup("fat".toUpperCase()).wasFound);
        assertFalse(dictionary.lookup("the".toUpperCase()).wasFound);
        assertFalse(dictionary.lookup("through".toUpperCase()).wasFound);
    }

    @Test
    public void loadIsAdditive() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        input = new BufferedReader(new FileReader("test/data/tiny_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertTrue(dictionary.lookup("bear".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("bare".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("bar".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("fear".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("dear".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("dog".toUpperCase()).wasFound);
        assertTrue(dictionary.lookup("cat".toUpperCase()).wasFound);
    }

    @Test
    public void dictionaryKnowsAboutsPrefixes() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("test/data/small_word_list.txt"));
        dictionary.load(input);
        input.close();

        assertFalse(dictionary.lookup("bear".toUpperCase()).isPrefix);
        assertTrue(dictionary.lookup("bea".toUpperCase()).isPrefix);
        assertTrue(dictionary.lookup("bar".toUpperCase()).isPrefix);
        assertFalse(dictionary.lookup("c".toUpperCase()).isPrefix);
    }
}