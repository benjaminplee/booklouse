package yardspoon.booklouse;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Desktop {

    Image getScreenshot(int x, int y, int width, int height) throws AWTException {
        return new BufferedImageAdapter(new Robot().createScreenCapture(new Rectangle(x, y, width, height)));
    }
}

class BufferedImageAdapter implements Image {

    private final static int[] pixel = new int[3];
    private final Raster raster;

    public BufferedImageAdapter(BufferedImage image) {
        raster = image.getRaster();
    }

    @Override
    public int[] getPixel(int x, int y) {
        return raster.getPixel(x, y, pixel);
    }

    @Override
    public int getHeight() {
        return raster.getHeight();
    }

    @Override
    public int getWidth() {
        return raster.getWidth();
    }
}