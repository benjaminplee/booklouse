package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Loading Dictionary.");
        Dictionary dictionary = new Dictionary();
        dictionary.load(new BufferedReader(new FileReader("src/data/word_list_big.txt")));

        WordFinder wordFinder = new WordFinder();
        wordFinder.setDictionary(dictionary);

        System.out.println("Building Board.");
        GraphicBoardBuilder boardBuilder = new GraphicBoardBuilder(new RobotDesktop());
        Tile[][] board = boardBuilder.buildBoard();

        System.out.println("Printing Board.");
        for (int x = 0; x < board.length; x++) {
            Tile[] col = board[x];

            System.out.print("  ");

            for (int y = 0; y < col.length; y++) {
                Tile tile = col[y];

                System.out.print(tile.letter() + " ");
            }
            
            System.out.println("");
        }

        System.out.println("Finding Words.");
        List<Word> words = wordFinder.findWords(board);

        System.out.println("Sorting Words");
        Collections.sort(words, Collections.reverseOrder(new ByLengthWordComparator()));

        System.out.println("Found " + words.size() + " words.");

        System.out.println("Sorting Words By Length.");
        // sort words here by either length or score

        System.out.println("Words:");
        for (Word word : words) {
            System.out.println("  " + word.string());
        }
    }

}
