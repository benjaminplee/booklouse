package yardspoon.booklouse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTileLoaderTest {

    private ReferenceTileLoader loader;

    @Before
    public void setup() {
        loader = new ReferenceTileLoader();
    }

    @Test
    public void loadsNormalTiles() throws IOException {
        List<String> referenceLetters = new ArrayList<String>(Arrays.asList("A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,QU,R,S,T,U,V,W,X,Y,Z".split(",")));

        Map<Image, Tile> result = loader.loadStandardReferenceTiles("test/data/normal_tiles");

        assertEquals(26, result.size());
        assertEquals(26, referenceLetters.size());

        for (Image image : result.keySet()) {
            Tile tile = result.get(image);

            assertTrue(tile.type() == Tile.Type.STANDARD);
            assertTrue(referenceLetters.contains(tile.letter()));
            referenceLetters.remove(tile.letter());
        }

        assertEquals(0, referenceLetters.size());
    }

    @Test
    public void loadGreenTiles() throws IOException {
        File greenTestDir = new File("test/data/green_tiles");
        List<String> foundLetterList = new ArrayList<String>();

        for (File file : greenTestDir.listFiles()) {
            if(file.isFile()) {
                String fileName = file.getName();
                assertTrue(fileName.endsWith(".png"));
                assertEquals(5, fileName.length());

                String letter = String.valueOf(fileName.charAt(0));

                foundLetterList.add(letter);
            }
        }

        Map<Image, Tile> result = loader.loadGreenReferenceTiles("test/data/green_tiles");
        
        assertEquals(foundLetterList.size(), result.size());

        for (Image image : result.keySet()) {
            Tile tile = result.get(image);

            assertTrue(tile.type() == Tile.Type.GREEN);
            assertTrue(foundLetterList.contains(tile.letter()));
            foundLetterList.remove(tile.letter());
        }

        assertEquals(0, foundLetterList.size());
    }
}