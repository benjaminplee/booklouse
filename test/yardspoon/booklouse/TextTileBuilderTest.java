
package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TextTileBuilderTest {
    private TextTileBuilder builder = null;

    @Before
    public void setup() {
        builder = new TextTileBuilder();
    }

    @Test
    public void singleLetterCreatesSingleTile() throws IOException {
        Tile [][] tiles = builder.buildTiles(new BufferedReader(new StringReader("a")));

        assertEquals(1, tiles.length);
        assertEquals(1, tiles[0].length);
        assertEquals(new Tile("A"), tiles[0][0]);
    }

    @Test
    public void twoSpacedLettersProduceTilesInSameColumn() throws IOException {
        Tile [][] tiles = builder.buildTiles(new BufferedReader(new StringReader("B Q")));

        assertEquals(1, tiles.length);
        assertEquals(2, tiles[0].length);
        assertEquals(new Tile("B"), tiles[0][0]);
        assertEquals(new Tile("Q"), tiles[0][1]);
    }

    @Test
    public void manySpacedLettersProduceColumn() throws IOException {
        Tile [][] tiles = builder.buildTiles(new BufferedReader(new StringReader("B Q J W W U S")));

        assertEquals(1, tiles.length);
        assertEquals(7, tiles[0].length);
        assertEquals(new Tile("B"), tiles[0][0]);
        assertEquals(new Tile("Q"), tiles[0][1]);
        assertEquals(new Tile("J"), tiles[0][2]);
        assertEquals(new Tile("W"), tiles[0][3]);
        assertEquals(new Tile("W"), tiles[0][4]);
        assertEquals(new Tile("U"), tiles[0][5]);
        assertEquals(new Tile("S"), tiles[0][6]);
    }

    @Test
    public void twoColumnsAreCreatedByStringWithNewLine() throws IOException {
        Tile [][] tiles = builder.buildTiles(new BufferedReader(new StringReader("B Q J W W U S\nA B C D E F G H")));

        assertEquals(2, tiles.length);
        assertEquals(7, tiles[0].length);
        assertEquals(new Tile("B"), tiles[0][0]);
        assertEquals(new Tile("Q"), tiles[0][1]);
        assertEquals(new Tile("J"), tiles[0][2]);
        assertEquals(new Tile("W"), tiles[0][3]);
        assertEquals(new Tile("W"), tiles[0][4]);
        assertEquals(new Tile("U"), tiles[0][5]);
        assertEquals(new Tile("S"), tiles[0][6]);

        assertEquals(8, tiles[1].length);
        assertEquals(new Tile("A"), tiles[1][0]);
        assertEquals(new Tile("B"), tiles[1][1]);
        assertEquals(new Tile("C"), tiles[1][2]);
        assertEquals(new Tile("D"), tiles[1][3]);
        assertEquals(new Tile("E"), tiles[1][4]);
        assertEquals(new Tile("F"), tiles[1][5]);
        assertEquals(new Tile("G"), tiles[1][6]);
    }

    @Test
    public void multipleColumns() throws IOException {
        Tile [][] tiles = builder.buildTiles(new BufferedReader(new StringReader("A\nB C D\nE F\nG H I")));

        assertEquals(4, tiles.length);

        assertEquals(1, tiles[0].length);
        assertEquals(new Tile("A"), tiles[0][0]);

        assertEquals(3, tiles[1].length);
        assertEquals(new Tile("B"), tiles[1][0]);
        assertEquals(new Tile("C"), tiles[1][1]);
        assertEquals(new Tile("D"), tiles[1][2]);

        assertEquals(2, tiles[2].length);
        assertEquals(new Tile("E"), tiles[2][0]);
        assertEquals(new Tile("F"), tiles[2][1]);

        assertEquals(3, tiles[3].length);
        assertEquals(new Tile("G"), tiles[3][0]);
        assertEquals(new Tile("H"), tiles[3][1]);
        assertEquals(new Tile("I"), tiles[3][2]);
    }
}