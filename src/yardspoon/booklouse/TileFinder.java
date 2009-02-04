package yardspoon.booklouse;

public class TileFinder {

    Image findTile(Image window, int x, int y) {
        if(x % 2 == 0) { // short column
            return window.subImage(198 + 48 * x, 36 + 48 * y, 48, 48);
        }
        else { // tall column
            return window.subImage(246 + 48 * (x - 1), 12 + 48 * y, 48, 48);
        }
    }
}
