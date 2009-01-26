package yardspoon.booklouse;

import org.junit.Test;
import static org.junit.Assert.*;

public class WordTest {

    @Test
    public void newWordHasBlankStringAndPointList() {
        Word word = new Word();

        assertEquals("", word.string());
        assertEquals(0, word.points().size());
    }

    @Test
    public void addingToWordAppendsToStringAndPointList() {
        Word word = new Word();

        word = word.createNewWordWithExtraTile(new Point(3, 4), "W");
        word = word.createNewWordWithExtraTile(new Point(5, 6), "QU");

        assertEquals("WQU", word.string());
        assertEquals(2, word.points().size());
        assertEquals(new Point(3, 4), word.points().get(0));
        assertEquals(new Point(5, 6), word.points().get(1));
    }
}