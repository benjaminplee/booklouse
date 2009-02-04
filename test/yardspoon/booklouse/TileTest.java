package yardspoon.booklouse;

import org.junit.Test;
import static org.junit.Assert.*;

public class TileTest {

    @Test
    public void singleLetterCreatesStandardTile() {
        Tile t = new Tile("A");

        assertEquals("A", t.letter());
    }

    @Test
    public void lowercaseLetterConverted() {
        Tile t = new Tile("a");

        assertEquals("A", t.letter());
    }

    @Test
    public void defaultTypeIsStandard() {
        Tile t = new Tile("A");

        assertEquals(Tile.Type.STANDARD, t.type());
    }

    @Test
    public void tileTypeCanBeSet() {
        Tile t = new Tile("B", Tile.Type.GREEN);

        assertEquals("B", t.letter());
        assertEquals(Tile.Type.GREEN, t.type());

        t = new Tile("C", Tile.Type.GOLD);

        assertEquals("C", t.letter());
        assertEquals(Tile.Type.GOLD, t.type());

        t = new Tile("D", Tile.Type.FIRE);

        assertEquals("D", t.letter());
        assertEquals(Tile.Type.FIRE, t.type());

        t = new Tile("E", Tile.Type.FIRE);

        assertEquals("E", t.letter());
        assertEquals(Tile.Type.FIRE, t.type());
    }
    
    @Test
    public void tilesCanBeEqual() {
        Tile t1 = new Tile("z", Tile.Type.GOLD);
        Tile t2 = new Tile("Z", Tile.Type.GOLD);
        
        assertEquals(t1, t1);
        assertEquals(t1, t2);
        assertEquals(t2, t1);
        
        Tile t3 = new Tile("z", Tile.Type.STANDARD);
        Tile t4 = new Tile("C", Tile.Type.GOLD);
        
        assertFalse(t1.equals(t3));
        assertFalse(t3.equals(t1));
        
        assertFalse(t1.equals(t4));
        assertFalse(t4.equals(t1));

        Tile t5 = new Tile("Z");

        assertEquals(t3, t5);
        assertEquals(t5, t3);
    }

    @Test
    public void qIsChangedToQU() {
        Tile t1 = new Tile("q");
        Tile t2 = new Tile("Q");

        assertEquals(t1, t2);
        assertEquals("QU", t1.letter());
    }

    @Test
    public void verifyToString() {
        assertEquals("[A>STANDARD]", new Tile("A").toString());
        assertEquals("[B>STANDARD]", new Tile("B").toString());
        assertEquals("[QU>STANDARD]", new Tile("Q").toString());
        assertEquals("[J>GREEN]", new Tile("J", Tile.Type.GREEN).toString());
        assertEquals("[X>FIRE]", new Tile("X", Tile.Type.FIRE).toString());
        assertEquals("[T>GOLD]", new Tile("T", Tile.Type.GOLD).toString());

    }
}