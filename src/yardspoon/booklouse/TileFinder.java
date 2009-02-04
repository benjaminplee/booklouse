package yardspoon.booklouse;

public class TileFinder {

    Image findTile(Image window, int x, int y) {
        return findTile(window, x, y, 0, 0);
    }

    Image findTile(Image window, int x, int y, int x_offset, int y_offset) {
        if(x % 2 == 0) { // short column
            return window.subImage(x_offset + 198 + 48 * x, y_offset + 36 + 48 * y, 48, 48);
        }
        else { // tall column
            return window.subImage(x_offset + 246 + 48 * (x - 1), y_offset + 12 + 48 * y, 48, 48);
        }
    }
}
