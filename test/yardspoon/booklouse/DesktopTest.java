package yardspoon.booklouse;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.junit.Test;
import static org.junit.Assert.*;

public class DesktopTest {

    @Test
    public void returnsCorrectlySizedValidImage() throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        RobotDesktop desktop = new RobotDesktop();

        Image image = desktop.getScreenshot(0, 0, screenSize.width, screenSize.height);

        assertNotNull(image);
        assertEquals(screenSize.width, image.getWidth());
        assertEquals(screenSize.height, image.getHeight());

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {
                int [] pixel = image.getPixel(x, y);

                assertNotNull(pixel);
                assertTrue("Pixel [0] wasn't >=0. Was: " + pixel[0], pixel[0] >= 0);
                assertTrue("Pixel [1] wasn't >=0. Was: " + pixel[1], pixel[1] >= 0);
                assertTrue("Pixel [2] wasn't >=0. Was: " + pixel[2], pixel[2] >= 0);
            }
        }
    }

    @Test
    public void returnsImageSameSizeAsDesktop() throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        RobotDesktop desktop = new RobotDesktop();

        Image image = desktop.getDesktop();

        assertNotNull(image);
        assertEquals(screenSize.width, image.getWidth());
        assertEquals(screenSize.height, image.getHeight());

        for(int x = 0; x < image.getWidth(); x++) {
            for(int y = 0; y < image.getHeight(); y++) {
                int [] pixel = image.getPixel(x, y);

                assertNotNull(pixel);
                assertTrue("Pixel [0] wasn't >=0. Was: " + pixel[0], pixel[0] >= 0);
                assertTrue("Pixel [1] wasn't >=0. Was: " + pixel[1], pixel[1] >= 0);
                assertTrue("Pixel [2] wasn't >=0. Was: " + pixel[2], pixel[2] >= 0);
            }
        }
    }
}