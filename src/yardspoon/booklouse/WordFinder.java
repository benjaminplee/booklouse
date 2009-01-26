package yardspoon.booklouse;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

    private Dictionary dictionary = null;

    List<Word> findWords(Tile[][] tiles) {
        List<Word> words = new ArrayList<Word>();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {

                buildWord(tiles, new Point(x, y), new Word(), words);
            }
        }

        return words;
    }

    void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    private void buildWord(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (!word.points().contains(point)) {
            word = word.createNewWordWithExtraTile(point, tiles[point.x][point.y].letter());

            LookupResult lookupResult = (dictionary == null) ? LookupResult.FOUND_PREFIX : dictionary.lookup(word.string());

            if (lookupResult.wasFound) {
                words.add(word);
            }

            if (lookupResult.isPrefix) {
                checkUp(tiles, point, word, words);
                checkDown(tiles, point, word, words);
                checkBottomRight(tiles, point, word, words);
                checkTopLeft(tiles, point, word, words);
                checkTopRight(tiles, point, word, words);
                checkBottomLeft(tiles, point, word, words);
            }
        }
    }

    private void checkBottomLeft(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (isShortColumn(point.x)) {
            if (point.x - 1 > 0) {
                buildWord(tiles, new Point(point.x - 1, point.y), word, words);
            }
        } else {
            if (point.x > 0 && point.y > 0) {
                buildWord(tiles, new Point(point.x - 1, point.y - 1), word, words);
            }
        }
    }

    private void checkBottomRight(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (isShortColumn(point.x)) {
            if (point.x + 1 < tiles.length) {
                buildWord(tiles, new Point(point.x + 1, point.y), word, words);
            }
        } else {
            if (point.x + 1 < tiles.length && point.y > 0) {
                buildWord(tiles, new Point(point.x + 1, point.y - 1), word, words);
            }
        }
    }

    private void checkDown(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (point.y > 0) {
            buildWord(tiles, new Point(point.x, point.y - 1), word, words);
        }
    }

    private void checkTopLeft(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (isShortColumn(point.x)) {
            if (point.x > 0 && point.y + 1 < tiles[point.x - 1].length) {
                buildWord(tiles, new Point(point.x - 1, point.y + 1), word, words);
            }
        } else {
            if (point.x > 0 && point.y < tiles[point.x - 1].length) {
                buildWord(tiles, new Point(point.x - 1, point.y), word, words);
            }
        }
    }

    private void checkTopRight(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (isShortColumn(point.x)) {
            if (point.x + 1 < tiles.length && point.y + 1 < tiles[point.x + 1].length) {
                buildWord(tiles, new Point(point.x + 1, point.y + 1), word, words);
            }
        } else {
            if (point.x + 1 < tiles.length && point.y < tiles[point.x + 1].length) {
                buildWord(tiles, new Point(point.x + 1, point.y), word, words);
            }
        }
    }

    private void checkUp(Tile[][] tiles, Point point, Word word, List<Word> words) {
        if (tiles[point.x].length > (point.y + 1)) {
            buildWord(tiles, new Point(point.x, point.y + 1), word, words);
        }
    }

    private boolean isShortColumn(int x) {
        return x % 2 == 0;
    }
}