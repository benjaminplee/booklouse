package yardspoon.booklouse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphicTileMatcherTest {

    private GraphicTileMatcher matcher = null;

    @Before
    public void setup() throws IOException {
        matcher = new GraphicTileMatcher();
    }

    @Test
    public void unknownGraphicTileReturnsNull() {
        Image graphicTile = new MockImage(10, 10);

        Tile tile = matcher.matchTile(graphicTile);

        assertNull(tile);
    }

    @Test
    public void matchNormalLetters() throws IOException {
        Map<Image, Tile> loadedTiles = new ReferenceTileLoader().loadStandardReferenceTiles("test/data/normal_tiles");

        assertEquals(26, loadedTiles.size());

        for (Image image : loadedTiles.keySet()) {
            assertEquals(loadedTiles.get(image), matcher.matchTile(image));
        }
    }

    @Test
    public void matchBothDTiles() throws IOException {
        Image d1 = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/d1.png")));
        Image d2 = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/d2.png")));

        assertEquals(new Tile("D"), matcher.matchTile(d1));
        assertEquals(new Tile("D"), matcher.matchTile(d2));
    }
}