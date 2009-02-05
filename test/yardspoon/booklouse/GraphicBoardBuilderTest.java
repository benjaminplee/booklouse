package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphicBoardBuilderTest {

    @Test
    public void buildsCorrectBoard1() throws IOException, Exception {
        Desktop desktop = new MockDesktop(ImageIO.read(new FileInputStream("test/data/example_desktop.png")));
        Tile[][] textTiles = new TextBoardBuilder().buildTiles(new BufferedReader(new FileReader("test/data/example_desktop_tiles.txt")));

        GraphicBoardBuilder builder = new GraphicBoardBuilder(desktop);
        Tile[][] graphicTiles = builder.buildBoard();

        assertEquals(textTiles.length, graphicTiles.length);

        for (int x = 0; x < textTiles.length; x++) {
            Tile[] textTilesColumn = textTiles[x];
            Tile[] graphicTilesColumn = graphicTiles[x];

            assertEquals(textTilesColumn.length, graphicTilesColumn.length);

            for (int y = 0; y < textTilesColumn.length; y++) {
                Tile textTile = textTilesColumn[y];
                Tile graphicTile = graphicTilesColumn[y];

                assertEquals(textTile, graphicTile);
            }
        }
    }

}