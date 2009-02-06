package yardspoon.booklouse;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import org.junit.Test;
import static org.junit.Assert.*;

public class BufferedImageAdapterTest {

    @Test
    public void adapterCorrectlyPassesToBufferedImage() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream("test/data/example_desktop.png"));
        Image image = new BufferedImageAdapter(bufferedImage);

        assertEquals(bufferedImage.getWidth(), image.getWidth());
        assertEquals(bufferedImage.getHeight(), image.getHeight());

        Raster raster = bufferedImage.getRaster();
        int [] pixel = new int [3];

        for(int x = 0; x < bufferedImage.getWidth(); x++) {
            for(int y = 0; y < bufferedImage.getHeight(); y++) {
                final int[] expectedPixel = raster.getPixel(x, y, pixel);
                final int[] testPixel = image.getPixel(x, y);
                assertEquals(expectedPixel[0], testPixel[0]);
                assertEquals(expectedPixel[1], testPixel[1]);
                assertEquals(expectedPixel[2], testPixel[2]);
            }
        }
    }

    @Test
    public void matches() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream("test/data/example_small_desktop.png"));
        Image image = new BufferedImageAdapter(bufferedImage);
        Image otherImage = new MockImage(10, 10);
        Image otherImage2 = new MockImage(image.getWidth(), image.getHeight());
        Image otherImage3 = new BufferedImageAdapter(bufferedImage);

        assertTrue(image.equals(image));
        assertTrue(image.equals(otherImage3));
        assertTrue(otherImage3.equals(image));

        assertFalse(image.equals(otherImage));
        assertFalse(otherImage.equals(image));
        assertFalse(image.equals(otherImage2));
        assertFalse(otherImage2.equals(image));

        assertFalse(image.equals(null));
    }

    @Test
    public void producesCorrectSubImage() throws IOException {
        final BufferedImage baseBufferedImage = ImageIO.read(new FileInputStream("test/data/example_desktop.png"));
        BufferedImage expectedBufferedImage = baseBufferedImage.getSubimage(1, 1, 10, 10);
        Image image = new BufferedImageAdapter(baseBufferedImage).subImage(1, 1, 10, 10);

        assertEquals(expectedBufferedImage.getWidth(), image.getWidth());
        assertEquals(expectedBufferedImage.getHeight(), image.getHeight());

        Raster raster = expectedBufferedImage.getRaster();
        int [] pixel = new int [3];

        for(int x = 0; x < expectedBufferedImage.getWidth(); x++) {
            for(int y = 0; y < expectedBufferedImage.getHeight(); y++) {
                final int[] expectedPixel = raster.getPixel(x, y, pixel);
                final int[] testPixel = image.getPixel(x, y);
                assertEquals(expectedPixel[0], testPixel[0]);
                assertEquals(expectedPixel[1], testPixel[1]);
                assertEquals(expectedPixel[2], testPixel[2]);
            }
        }
    }

    @Test
    public void fuzzyEqualsFindsMatchesWithThresholdOnDs() throws IOException {
        Image d1 = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/D1.png")));
        Image d2 = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/D2.png")));

        assertTrue(d1.fuzzyEquals(d1));
        assertTrue(d2.fuzzyEquals(d2));
        assertTrue(d1.fuzzyEquals(d2));
        assertTrue(d2.fuzzyEquals(d1));
    }

    @Test
    public void fuzzyEqualsFindsMatchesWithThresholdOnAs() throws IOException {
        Image a1 = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/A1.png")));
        Image a2 = new BufferedImageAdapter(ImageIO.read(new FileInputStream("test/data/A2.png")));

        assertTrue(a1.fuzzyEquals(a1));
        assertTrue(a2.fuzzyEquals(a2));
        assertTrue(a1.fuzzyEquals(a2));
        assertTrue(a2.fuzzyEquals(a1));
    }

    @Test
    public void imagesOnlyFuzzyEqualsCorrectImageAndNotOthers() throws IOException {
        Map<Image, Tile> loadedTiles = new ReferenceTileLoader().loadStandardReferenceTiles("test/data/normal_tiles");

        for (Image baseImage : loadedTiles.keySet()) {
            Tile baseTile = loadedTiles.get(baseImage);

            for (Image testImage : loadedTiles.keySet()) {
                Tile testTile = loadedTiles.get(testImage);

                if(baseTile.equals(testTile)) {
                    assertTrue(baseImage.fuzzyEquals(testImage));
                }
                else {
                    assertFalse(baseImage.fuzzyEquals(testImage));
                }
            }
        }
    }
}