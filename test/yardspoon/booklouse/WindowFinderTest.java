package yardspoon.booklouse;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;
import static org.junit.Assert.*;

public class WindowFinderTest {

    @Test
    public void findTheWindowInASmallDesktopImage() throws IOException {
        WindowFinder finder = new WindowFinder(new MockDesktop(ImageIO.read(new FileInputStream("test\\data\\example_small_desktop.png"))));

        Point point = finder.findWindow();

        assertEquals(new Point(33, 41), point);
    }

    @Test
    public void findWindowOnLargeDesktop() throws IOException {
        WindowFinder finder = new WindowFinder(new MockDesktop(ImageIO.read(new FileInputStream("test\\data\\example_desktop.png"))));

        Point point = finder.findWindow();

        assertEquals(new Point(415, 367), point);
    }

    @Test
    public void returnNullIfCantFindWindow() throws IOException {
        WindowFinder finder = new WindowFinder(new MockDesktop(ImageIO.read(new FileInputStream("test\\data\\no_window_desktop.png"))));

        Point point = finder.findWindow();

        assertNull(point);
    }
}

class MockDesktop implements Desktop {

    private final BufferedImage image;

    MockDesktop(BufferedImage image) {
        this.image = image;
    }

    @Override
    public Image getDesktop() throws Exception {
        return new BufferedImageAdapter(image);
    }

    @Override
    public Image getScreenshot(int x, int y, int width, int height) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}