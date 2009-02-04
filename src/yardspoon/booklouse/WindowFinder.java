package yardspoon.booklouse;

import java.io.FileInputStream;
import javax.imageio.ImageIO;

public class WindowFinder {
    private final Desktop desktop;

    WindowFinder(Desktop desktop) {
        this.desktop = desktop;
    }

    Point findWindow() {
        SubImageFinder finder = new SubImageFinder();

        try {
            final Image baseDesktop = desktop.getDesktop();
            final Image targetWindowReference = new BufferedImageAdapter(ImageIO.read(new FileInputStream("src/data/reference/top_left_board.png")));


            return finder.find(baseDesktop,targetWindowReference);
        } catch (Exception ex) {
            throw new RuntimeException("Exception while finding the window in the given desktop image.", ex);
        }
    }
}
