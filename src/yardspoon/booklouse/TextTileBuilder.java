package yardspoon.booklouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TextTileBuilder {

    Tile[][] buildTiles(BufferedReader input) throws IOException {
        List<Tile[]> listOfTileArrays = new ArrayList<Tile[]>();
        String column = null;

        while((column = input.readLine()) != null) {
            String[] letters = column.split(" ");
            Tile[] colTiles = new Tile[letters.length];

            for(int i = 0; i < letters.length; i++) {
                colTiles[i] = new Tile(letters[i]);
            }

            listOfTileArrays.add(colTiles);
        }

        Tile[][] tiles = new Tile[listOfTileArrays.size()][];

        for(int i = 0; i < tiles.length; i++) {
            tiles[i] = listOfTileArrays.get(i);
        }

        return tiles;
    }

}
