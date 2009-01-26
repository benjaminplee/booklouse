package yardspoon.booklouse;

public class SubImageFinder {

    Point find(Image base, Image target) {
        Point result = null;

        for (int base_x = 0; base_x < base.getWidth() - target.getWidth() + 1; base_x++) {
            for (int base_y = 0; base_y < base.getHeight() - target.getHeight() + 1; base_y++) {
                
                boolean foundMistake = false;

                for(int target_x = 0; target_x < target.getWidth() && !foundMistake; target_x++) {
                    for(int target_y = 0; target_y < target.getHeight() && !foundMistake; target_y++) {
                        foundMistake = !pixelsAreEqual(base.getPixel(base_x + target_x, base_y + target_y), target.getPixel(target_x, target_y));
                    }
                }

                if (!foundMistake) {
                    return new Point(base_x, base_y);
                }
            }
        }

        return result;
    }

    private boolean pixelsAreEqual(final int[] basePixel, final int[] targetPixel) {
        return basePixel[0] == targetPixel[0] && basePixel[1] == targetPixel[1] && basePixel[2] == targetPixel[2];
    }
}
