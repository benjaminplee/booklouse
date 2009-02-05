package yardspoon.booklouse;

import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WindowFinder {

    Point findWindow(Image snapshot) throws IOException {
        final Image targetWindowReference = new BufferedImageAdapter(ImageIO.read(new FileInputStream("src/data/reference/top_left_board.png")));
        return new SubImageFinder().find(snapshot, targetWindowReference);
    }
}
