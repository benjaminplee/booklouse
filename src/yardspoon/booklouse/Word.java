package yardspoon.booklouse;

import java.util.ArrayList;
import java.util.List;

class Word {

    private static String BLANK_STRING = "";
    private static List<Point> EMPTY_POINTS = new ArrayList<Point>();
    private final String string;
    private final List<Point> points;

    Word() {
        string = BLANK_STRING;
        points = EMPTY_POINTS;
    }

    Word(String string, List<Point> points) {
        this.string = string;
        this.points = points;
    }

    Word createNewWordWithExtraTile(Point point, String letter) {
        List<Point> newPoints = new ArrayList<Point>(points());
        newPoints.add(point);

        return new Word(string() + letter, newPoints);
    }

    String string() {
        return string;
    }

    List<Point> points() {
        return points;
    }
}
