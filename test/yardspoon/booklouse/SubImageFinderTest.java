package yardspoon.booklouse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubImageFinderTest {

    private SubImageFinder finder = null;

    @Before
    public void setUp() {
        finder = new SubImageFinder();
    }

    @Test
    public void findWhitePixelInSmallBlackSample() {
        MockImage base = new MockImage(4, 4);
        base.setPixel(1, 1, 255, 255, 255);

        MockImage target = new MockImage(1, 1);
        target.setPixel(0, 0, 255, 255, 255);

        Point result = finder.find(base, target);

        assertNotNull(result);
        assertEquals(new Point(1, 1), result);
    }

    @Test
    public void returnsNullIfTargetIsNotFound() {
        MockImage base = new MockImage(4, 4);
        base.setPixel(1, 1, 255, 255, 255);

        MockImage target = new MockImage(1, 1);
        target.setPixel(0, 0, 20, 20, 20);

        Point result = finder.find(base, target);

        assertNull(result);
    }

    @Test
    public void findsMultiPixelTargetMatchingAllPixels() {
        MockImage base = new MockImage(4, 4);
        base.setPixel(0, 0, 255, 255, 255);
        base.setPixel(1, 1, 255, 255, 255);
        base.setPixel(1, 2, 13, 13, 13);

        MockImage target = new MockImage(1, 2);
        target.setPixel(0, 0, 255, 255, 255);
        target.setPixel(0, 1, 13, 13, 13);

        Point result = finder.find(base, target);

        assertNotNull(result);
        assertEquals(new Point(1, 1), result);
    }

    @Test
    public void canFindTargetInFarthestCorner() {
        MockImage base = new MockImage(4, 4);
        base.setPixel(0, 0, 255, 255, 255);
        base.setPixel(3, 2, 255, 255, 255);
        base.setPixel(3, 3, 13, 13, 13);

        MockImage target = new MockImage(1, 2);
        target.setPixel(0, 0, 255, 255, 255);
        target.setPixel(0, 1, 13, 13, 13);

        Point result = finder.find(base, target);

        assertNotNull(result);
        assertEquals(new Point(3, 2), result);
    }
}

class MockImage implements Image {

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

}