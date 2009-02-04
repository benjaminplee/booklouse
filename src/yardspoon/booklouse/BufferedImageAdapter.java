package yardspoon.booklouse;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

class BufferedImageAdapter implements Image {

    private final int x_offset;
    private final int y_offset;
    private final int externalWidth;
    private final int externalHeight;
    private final int[] pixel = new int[3];
    private final Raster raster;

    public BufferedImageAdapter(BufferedImage image) {
        this(image.getRaster(), 0, 0, image.getWidth(), image.getHeight());
    }

    private BufferedImageAdapter(Raster raster, int x_offset, int y_offset, int externalWidth, int externalHeight) {
        this.raster = raster;
        this.x_offset = x_offset;
        this.y_offset = y_offset;
        this.externalWidth = externalWidth;
        this.externalHeight = externalHeight;
    }

    @Override
    public int[] getPixel(int x, int y) {
        if (x >= externalWidth) {
            throw new IndexOutOfBoundsException("Invalid pixel location. x: " + x + ">= width: " + getWidth());
        }

        if (y >= externalHeight) {
            throw new IndexOutOfBoundsException("Invalid pixel location. y: " + y + ">= height: " + getHeight());
        }

        x += x_offset;
        y += y_offset;

        return raster.getPixel(x, y, pixel);
    }

    @Override
    public int getHeight() {
        return externalHeight;
    }

    @Override
    public int getWidth() {
        return externalWidth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Image)) {
            return false;
        }

        Image other = (Image) obj;

        return fuzzyEquals(other, 0);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.raster != null ? this.raster.hashCode() : 0);
        return hash;
    }

    @Override
    public Image subImage(int x, int y, int width, int height) {
        return new BufferedImageAdapter(raster, x, y, width, height);
    }

    @Override
    public boolean fuzzyEquals(Image other) {
        return fuzzyEquals(other, 5000);
    }

    private boolean fuzzyEquals(Image other, int threshold) {
        if (other.getWidth() != getWidth() || other.getHeight() != getHeight()) {
            return false;
        }

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                final int[] thisPixel = getPixel(x, y);
                final int[] otherPixel = other.getPixel(x, y);

                boolean thisIsBlack = isBlackPixel(thisPixel);
                boolean otherIsBlack = isBlackPixel(otherPixel);

                if((thisIsBlack && !otherIsBlack) || (otherIsBlack && !thisIsBlack)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isBlackPixel(int[] p) {
        return p[0] == 0 && p[1] == 0 && p[2] == 0;
    }
}
