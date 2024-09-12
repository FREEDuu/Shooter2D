import java.awt.image.*;;

public class ReadImage {
    public static int[][] get2DPixelArraySlow(BufferedImage sampleImage) {
        int width = sampleImage.getWidth();
        int height = sampleImage.getHeight();
        System.out.println(width);
        System.out.println(height);

        int[][] result = new int[height][width];
    
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result[row][col] = sampleImage.getRGB(col, row);
            }
        }
        return result;
    
    }
}
