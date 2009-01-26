package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dictionary {

    private final List<String> wordList;

    Dictionary() {
        wordList = new ArrayList<String>();
    }

    void load(BufferedReader bufferedReader) throws IOException {
        String word = null;
        while ((word = bufferedReader.readLine()) != null) {
            wordList.add(word);
        }

        Collections.sort(wordList);
    }

    int size() {
        return wordList.size();
    }

    LookupResult lookup(String candidate) {
        int index = Collections.binarySearch(wordList, candidate);

        if (index >= 0) {
            if (index < size() - 1 && wordList.get(index + 1).startsWith(candidate)) {
                return LookupResult.FOUND_PREFIX;
            }

            return LookupResult.FOUND_NOT_PREFIX;
        } else {
            index = (index + 1) * -1;

            if (index < size() && wordList.get(index).startsWith(candidate)) {
                return LookupResult.NOT_FOUND_PREFIX;
            }

            return LookupResult.NOT_FOUND_NOT_PREFIX;
        }
    }
}
