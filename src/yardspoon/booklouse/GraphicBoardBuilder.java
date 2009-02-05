package yardspoon.booklouse;

import java.io.IOException;

public class GraphicBoardBuilder {
    private final Desktop desktop;
    private final TileFinder tileFinder;
    private final GraphicTileMatcher matcher;
    private final WindowFinder windowFinder;

    GraphicBoardBuilder(Desktop desktop) throws IOException {
        this.desktop = desktop;
        tileFinder = new TileFinder();
        matcher = new GraphicTileMatcher();
        windowFinder = new WindowFinder();
    }

    Tile[][] buildBoard() throws IOException, Exception {
        Image snapShot = desktop.getDesktop();
        Point windowLoc = windowFinder.findWindow(snapShot);
        
        Tile[][] tiles = new Tile[7][];

        for (int x = 0; x < tiles.length; x++) {
            final int height = (x % 2 == 0) ? 7 : 8;
            tiles[x] = new Tile[height];

            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = matcher.matchTile(tileFinder.findTile(snapShot, x, y, windowLoc.x, windowLoc.y));
            }
        }

        return tiles;
    }
}
