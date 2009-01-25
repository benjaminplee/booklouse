package yardspoon.booklouse;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {

    List<String> findWords(Tile[][] tiles) {
        List<String> words = new ArrayList<String>();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {

                buildWord(tiles, x, y, "", words);
            }
        }

        return words;
    }

    private void buildWord(Tile[][] tiles, int x, int y, String word, List<String> words) {
        if (word.indexOf(tiles[x][y].letter()) == -1) {
            word += tiles[x][y].letter();

            words.add(word);

            // up
            if (tiles[x].length > (y + 1)) {
                buildWord(tiles, x, y + 1, word, words);
            }

            // down
            if (y > 0) {
                buildWord(tiles, x, y - 1, word, words);
            }

            // bottom right
            if (x % 2 == 0) { // short column
                if (x + 1 < tiles.length) {
                    buildWord(tiles, x + 1, y, word, words);
                }
            } else { // tall column
                if (x + 1 < tiles.length && y > 0) {
                    buildWord(tiles, x + 1, y - 1, word, words);
                }
            }

            // top left
            if (x % 2 == 0) { // short column
                if (x > 0 && y + 1 < tiles[x - 1].length) {
                    buildWord(tiles, x - 1, y + 1, word, words);
                }
            } else { // tall column
                if (x > 0 && y < tiles[x - 1].length) {
                    buildWord(tiles, x - 1, y, word, words);
                }
            }

            // top right
            if (x % 2 == 0) { // short column
                if (x + 1 < tiles.length && y + 1 < tiles[x + 1].length) {
                    buildWord(tiles, x + 1, y + 1, word, words);
                }
            } else { // tall column
                if (x + 1 < tiles.length && y < tiles[x + 1].length) {
                    buildWord(tiles, x + 1, y, word, words);
                }
            }

            // bottom left
            if (x % 2 == 0) { // short column
                if (x - 1 > 0) {
                    buildWord(tiles, x - 1, y, word, words);
                }
            } else { // tall column
                if (x > 0 && y > 0) {
                    buildWord(tiles, x - 1, y - 1, word, words);
                }
            }
        }
    }
}