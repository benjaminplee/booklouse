package yardspoon.booklouse;

public interface Image {
    int [] getPixel(int x, int y);
    int getHeight();
    int getWidth();
    Image subImage(int x, int y, int width, int height);
    boolean fuzzyEquals(Image other);

    @Override
    boolean equals(Object other);

    @Override
    int hashCode();
}
