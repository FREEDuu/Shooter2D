import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Utils{

    public static BufferedImage rotate(BufferedImage bimg, double angle) {
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        Graphics2D graphic = bimg.createGraphics();
        graphic.rotate(angle, w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return bimg;
    }

    public static double getAngle(int midx, int midy, int mousex, int mousey){
        double difx = Math.abs(midx - mousex);
        double dify = Math.abs(midy - mousey);

        if(midx > mousex && midy > mousey){
            return +Math.atan(dify/difx) - (Math.PI/2 * 2) ;
        }
        else if(midx < mousex && midy > mousey){
            return -Math.atan(dify/difx) ;
        }
        else if(midx > mousex && midy < mousey){
            return (Math.PI/2 * 2) - Math.atan(dify/difx);
        }
        return Math.atan(dify/difx);
    }

}

