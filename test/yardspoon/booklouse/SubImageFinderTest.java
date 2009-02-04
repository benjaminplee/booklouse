package yardspoon.booklouse;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
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

    @Test
    public void findImageBasedOnBufferedImage() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream("test/data/example_desktop.png"));
        Image base = new BufferedImageAdapter(bufferedImage);
        Image target = new BufferedImageAdapter(bufferedImage.getSubimage(20, 30, 20, 20));

        Point result = finder.find(base, target);

        assertEquals(new Point(20, 30), result);
    }
}