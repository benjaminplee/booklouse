package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TileFinderTest {

    private static GraphicTileMatcher matcher;
    private TileFinder finder = null;

    @BeforeClass
    public static void classSetup() throws IOException {
        matcher = new GraphicTileMatcher();
    }

    @Before
    public void setup() {
        finder = new TileFinder();
    }

    @Test
    public void findTopLeftImage() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm1.png")));

        Image topLeftImage = finder.findTile(window, 0, 0);

        assertEquals(new Tile("N"), matcher.matchTile(topLeftImage));
    }

    @Test
    public void findFirstColumn() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm1.png")));
        String[] firstColLetters = "N,N,S,M,A,I,I".split(",");

        for(int i = 0; i < firstColLetters.length; i++) {
            Image tileImage = finder.findTile(window, 0, i);
            assertEquals(new Tile(firstColLetters[i]), matcher.matchTile(tileImage));
        }
    }

    @Test
    public void findSecondColumn() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm1.png")));
        String[] firstColLetters = "R,A,M,E,J,H,D,A".split(",");

        for(int i = 0; i < firstColLetters.length; i++) {
            Image tileImage = finder.findTile(window, 1, i);
            assertEquals(new Tile(firstColLetters[i]), matcher.matchTile(tileImage));
        }
    }

    @Test
    public void figureOutBugWith_5_0() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm1.png")));

        assertEquals(new Tile("O"), matcher.matchTile(finder.findTile(window, 5, 0)));
    }
    
    @Test
    public void findWholeBoard() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm1.png")));
        Tile[][] tiles = new TextTileBuilder().buildTiles(new BufferedReader(new FileReader("test/data/bookworm1_tiles.txt")));

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile tile = tiles[x][y];
                assertEquals(tile, matcher.matchTile(finder.findTile(window, x, y)));
            }
        }
    }

    @Test
    public void findWholeBoard2() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm2.png")));
        Tile[][] tiles = new TextTileBuilder().buildTiles(new BufferedReader(new FileReader("test/data/bookworm2_tiles.txt")));

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile tile = tiles[x][y];
                assertEquals("Tile at " + x + "," + y + " didn't match.", tile, matcher.matchTile(finder.findTile(window, x, y)));
            }
        }
    }

    @Test
    public void findWholeBoard3() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm3.png")));
        Tile[][] tiles = new TextTileBuilder().buildTiles(new BufferedReader(new FileReader("test/data/bookworm3_tiles.txt")));

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile tile = tiles[x][y];
                assertEquals("Tile at " + x + "," + y + " didn't match.", tile, matcher.matchTile(finder.findTile(window, x, y)));
            }
        }
    }

    @Test
    public void findWholeBoard4() throws IOException {
        Image window = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/bookworm4.png")));
        Tile[][] tiles = new TextTileBuilder().buildTiles(new BufferedReader(new FileReader("test/data/bookworm4_tiles.txt")));

        for(int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                Tile tile = tiles[x][y];
                assertEquals("Tile at " + x + "," + y + " didn't match.", tile, matcher.matchTile(finder.findTile(window, x, y)));
            }
        }
    }

    // test to add ability to call with offset, using large window

}