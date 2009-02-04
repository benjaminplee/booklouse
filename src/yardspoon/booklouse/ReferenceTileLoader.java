package yardspoon.booklouse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class ReferenceTileLoader {

    Map<Image, Tile> loadDirectory(String folderPath) throws IOException {
        Map<Image, Tile> imageTileMap = new HashMap<Image, Tile>();
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    BufferedImage image = ImageIO.read(file);

                    if (image != null) {
                        String fileNameWithoutExtension = file.getName().split("\\.")[0];

                        imageTileMap.put(new BufferedImageAdapter(image), new Tile(fileNameWithoutExtension));
                    }
                }
            }
        }

        return imageTileMap;
    }
}
