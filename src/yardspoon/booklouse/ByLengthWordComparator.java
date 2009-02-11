package yardspoon.booklouse;

import java.util.Comparator;

public class ByLengthWordComparator implements Comparator<Word> {

    @Override
    public int compare(Word w1, Word w2) {
        if(w2 == null) {
            return -1;
        }
        else if(w1 == null) {
            return 1;
        }

        int ws1 = w1.string().length();
        int ws2 = w2.string().length();

        if(ws1 == ws2) {
            return 0;
        }
        else if(ws1 > ws2) {
            return 1;
        }

        return -1;
    }

}
