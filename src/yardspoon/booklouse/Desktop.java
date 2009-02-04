
package yardspoon.booklouse;

public interface Desktop {

    Image getDesktop() throws Exception;

    Image getScreenshot(int x, int y, int width, int height) throws Exception;

}
