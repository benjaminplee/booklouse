package yardspoon.booklouse;

import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTileLoaderTest {

    @Test
    public void loadsNormalTiles() throws IOException {
        ReferenceTileLoader loader = new ReferenceTileLoader();
        String[] referenceLetters = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

        Map<Image, Tile> result = loader.loadDirectory("test/data/normal_tiles");

        assertEquals(26, result.size());
        assertEquals(26, referenceLetters.length);
    }

    




}