package yardspoon.booklouse;

public class MockImage implements Image {

    private final int width;
    private final int height;
    private final int[][][] pixels;

    MockImage(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width][height][3];
    }

    void setPixel(int x, int y, int red, int green, int blue) {
        pixels[x][y][0] = red;
        pixels[x][y][1] = green;
        pixels[x][y][2] = blue;
    }

    @Override
    public int[] getPixel(int x, int y) {
        return pixels[x][y];
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Image)) {
            return false;
        }

        Image other = (Image)obj;

        if (other.getWidth() != getWidth() || other.getHeight() != getHeight()) {
            return false;
        }

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                final int[] thisPixel = getPixel(x, y);
                final int[] otherPixel = other.getPixel(x, y);

                if (thisPixel[0] != otherPixel[0] || thisPixel[1] != otherPixel[1] || thisPixel[2] != otherPixel[2]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.width;
        hash = 37 * hash + this.height;
        hash = 37 * hash + (this.pixels != null ? this.pixels.hashCode() : 0);
        return hash;
    }

    @Override
    public Image subImage(int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean fuzzyEquals(Image other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}