package yardspoon.booklouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ByLengthComparatorTest {

    @Test
    public void sortedElementStaySorted() {
        ByLengthWordComparator comp = new ByLengthWordComparator();
        List<Word> words = new ArrayList<Word>();
        words.add(new Word("cat", null));
        words.add(new Word("dog", null));
        words.add(new Word("fish", null));

        assertEquals("cat", words.get(0).string());
        assertEquals("dog", words.get(1).string());
        assertEquals("fish", words.get(2).string());

        Collections.sort(words, comp);

        assertEquals("cat", words.get(0).string());
        assertEquals("dog", words.get(1).string());
        assertEquals("fish", words.get(2).string());
    }

    @Test
    public void sortsElementsByLength() {
        ByLengthWordComparator comp = new ByLengthWordComparator();
        List<Word> words = new ArrayList<Word>();
        words.add(new Word("dog", null));
        words.add(new Word("flying_fish", null));
        words.add(new Word("fish", null));
        words.add(new Word("cow", null));

        assertEquals("dog", words.get(0).string());
        assertEquals("flying_fish", words.get(1).string());
        assertEquals("fish", words.get(2).string());
        assertEquals("cow", words.get(3).string());

        Collections.sort(words, comp);

        assertEquals("dog", words.get(0).string());
        assertEquals("cow", words.get(1).string());
        assertEquals("fish", words.get(2).string());
        assertEquals("flying_fish", words.get(3).string());
    }
}