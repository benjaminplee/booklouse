package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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

        List<Word> words = finder.findWords(tiles);

        assertEquals(1, words.size());
        assertEquals("A", words.get(0).string());
    }

    @Test
    public void findsWordsGoingUpAndDown() {
        Tile[][] tiles = new Tile[][]{{new Tile("A"), new Tile("B")}};

        List<Word> words = finder.findWords(tiles);

        assertEquals(4, words.size());
        assertEquals("A", words.get(0).string());
        assertEquals("AB", words.get(1).string());
        assertEquals("B", words.get(2).string());
        assertEquals("BA", words.get(3).string());
    }

    @Test
    public void findWordsLowerRightAndUpperLeft() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D")}};

        List<Word> words = finder.findWords(tiles);

        assertEquals(4, words.size());
        assertEquals("C", words.get(0).string());
        assertEquals("CD", words.get(1).string());
        assertEquals("D", words.get(2).string());
        assertEquals("DC", words.get(3).string());
    }

    @Test
    public void findWordsLowerLeftAndUpperRight() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}};

        List<Word> words = finder.findWords(tiles);

        assertEquals(15, words.size());
        assertEquals("C", words.get(0).string());
        assertEquals("CD", words.get(1).string());
        assertEquals("CDE", words.get(2).string());
        assertEquals("CE", words.get(3).string());
        assertEquals("CED", words.get(4).string());
        assertEquals("D", words.get(5).string());
        assertEquals("DE", words.get(6).string());
        assertEquals("DEC", words.get(7).string());
        assertEquals("DC", words.get(8).string());
        assertEquals("DCE", words.get(9).string());
        assertEquals("E", words.get(10).string());
        assertEquals("ED", words.get(11).string());
        assertEquals("EDC", words.get(12).string());
        assertEquals("EC", words.get(13).string());
        assertEquals("ECD", words.get(14).string());
    }

    @Test
    public void findAllPossibleWords() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}, {new Tile("F")}};

        List<Word> words = finder.findWords(tiles);

        assertEquals(10 + 10 + 11 + 11, words.size());
    }

    @Test
    public void sameLetterDifferentSpotsDoesntProduceCycles() {
        Tile[][] tiles = new Tile[][]{{new Tile("A")}, {new Tile("A"), new Tile("A")}, {new Tile("A")}};

        List<Word> words = finder.findWords(tiles);

        assertEquals(10 + 10 + 11 + 11, words.size());
    }

    @Test
    public void wordsReturnedHaveTileLocationInformation() {
        Tile[][] tiles = new Tile[][]{{new Tile("A")}};

        List<Word> words = finder.findWords(tiles);

        assertEquals(1, words.size());
        assertEquals("A", words.get(0).string());
        assertEquals(1, words.get(0).points().size());
        assertEquals(new Point(0, 0), words.get(0).points().get(0));
    }

    @Test
    public void longerWordsHaveCorrectPoints() {
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}};
        Point c = new Point(0, 0);
        Point d = new Point(1, 0);
        Point e = new Point(1, 1);

        List<Word> words = finder.findWords(tiles);

        assertEquals("CDE", words.get(2).string());
        assertEquals(3, words.get(2).points().size());
        assertEquals(c, words.get(2).points().get(0));
        assertEquals(d, words.get(2).points().get(1));
        assertEquals(e, words.get(2).points().get(2));

        assertEquals("CED", words.get(4).string());
        assertEquals(3, words.get(4).points().size());
        assertEquals(c, words.get(4).points().get(0));
        assertEquals(e, words.get(4).points().get(1));
        assertEquals(d, words.get(4).points().get(2));
    }

    @Test
    public void filtersDependingOnGivenDictionary() throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.load(new BufferedReader(new StringReader(("CED\nE"))));
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}};

        finder.setDictionary(dictionary);

        List<Word> words = finder.findWords(tiles);

        assertEquals(2, words.size());
        assertEquals("CED", words.get(0).string());
        assertEquals("E", words.get(1).string());
    }

    @Test
    public void stopsLookingIfCurrentWordIsNotAPrefixInDictionary() throws IOException {
        RecordingDictionary dictionary = new RecordingDictionary();
        dictionary.load(new BufferedReader(new StringReader(("CED\nE"))));
        Tile[][] tiles = new Tile[][]{{new Tile("C")}, {new Tile("D"), new Tile("E")}};

        finder.setDictionary(dictionary);

        List<Word> words = finder.findWords(tiles);

        List<String> lookedUpWords = dictionary.getLookedUpWords();

        assertEquals(6, lookedUpWords.size());
        assertEquals("C", lookedUpWords.get(0));
        assertEquals("CD", lookedUpWords.get(1));
        assertEquals("CE", lookedUpWords.get(2));
        assertEquals("CED", lookedUpWords.get(3));
        assertEquals("D", lookedUpWords.get(4));
        assertEquals("E", lookedUpWords.get(5));
    }
}

class RecordingDictionary extends Dictionary {

    private List<String> lookedUpWords = new ArrayList<String>();

    @Override
    LookupResult lookup(String candidate) {
        lookedUpWords.add(candidate);
        return super.lookup(candidate);
    }

    public List<String> getLookedUpWords() {
        return lookedUpWords;
    }
}