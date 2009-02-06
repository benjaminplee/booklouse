package yardspoon.booklouse;

import java.io.IOException;
import java.util.Map;

public class GraphicTileMatcher {
    private final Map<Image, Tile> tileMap;

    public GraphicTileMatcher() throws IOException {
        tileMap = new ReferenceTileLoader().loadStandardReferenceTiles("src/data/normal_tiles");
    }

    Tile matchTile(Image graphicTile) {
        for(Image referenceImage : tileMap.keySet()) {
            if(referenceImage.fuzzyEquals(graphicTile)) {
                return tileMap.get(referenceImage);
            }
        }

        return null;
    }
}