package yardspoon.booklouse;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordFinderTest {

    private WordFinder finder = null;

    @Before
    public void setUp() {
        finder = new WordFinder();
    }

    @Test
    public void findsOneSingleLetteredWord() {
        Tile[][] tiles = new Tile[][]{{new Tile("A")}};

        List<String> words = finder.findWords(tiles);

        assertEquals(1, words.size());
        assertEquals("A", words.get(0));
    }

    @Test
    public void findsWordsGoingUpAndDown() {
        Tile[][] tiles = new Tile[][]{{new Tile("A"), new Tile("B")}};

        List<String> words = finder.findWords(tiles);

        assertEquals(4, words.size());
        assertEquals("A", words.get(0));
        assertEquals("AB", words.get(1));
        assertEquals("B", words.get(2));
        assertEquals("BA", words.get(3));
    }
    
    @Test
    public void findWordsLowerRightAndUpperLeft() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D")}};
        
        List<String> words = finder.findWords(tiles);
        
        assertEquals(4, words.size());
        assertEquals("C", words.get(0));
        assertEquals("CD", words.get(1));
        assertEquals("D", words.get(2));
        assertEquals("DC", words.get(3));
    }

    @Test
    public void findWordsLowerLeftAndUpperRight() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}};

        List<String> words = finder.findWords(tiles);

        assertEquals(15, words.size());
        assertTrue(words.contains("C"));
        assertTrue(words.contains("CD"));
        assertTrue(words.contains("CDE"));
        assertTrue(words.contains("CE"));
        assertTrue(words.contains("CED"));
        assertTrue(words.contains("D"));
        assertTrue(words.contains("DE"));
        assertTrue(words.contains("DEC"));
        assertTrue(words.contains("DC"));
        assertTrue(words.contains("DCE"));
        assertTrue(words.contains("E"));
        assertTrue(words.contains("ED"));
        assertTrue(words.contains("EDC"));
        assertTrue(words.contains("EC"));
        assertTrue(words.contains("ECD"));
    }

    @Test
    public void findAllPossibleWords() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}, {new Tile("F")}};

        List<String> words = finder.findWords(tiles);

        assertEquals(10 + 10 + 11 + 11, words.size());
    }

    @Test
    public void sameLetterDifferentSpotsDoesntProduceCycles() {
        Tile[][] tiles = new Tile[][]{{new Tile("A")}, {new Tile("A"), new Tile("A")}, {new Tile("A")}};

        List<String> words = finder.findWords(tiles);

        assertEquals(10 + 10 + 11 + 11, words.size());
    }
}