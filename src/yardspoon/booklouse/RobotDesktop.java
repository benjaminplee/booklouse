package yardspoon.booklouse;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

public class RobotDesktop implements Desktop {

    @Override
    public Image getScreenshot(int x, int y, int width, int height) throws AWTException {
        return new BufferedImageAdapter(new Robot().createScreenCapture(new Rectangle(x, y, width, height)));
    }

    @Override
    public Image getDesktop() throws AWTException {
        return new BufferedImageAdapter(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));
    }
}