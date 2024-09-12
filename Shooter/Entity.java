import java.awt.image.BufferedImage;

public class Entity {

    // classe generica per implementare poi il player ed i mob
    // all'interno ci saranno funzioni generiche come paintComponent ecc

    public int x,y,speed ;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4 ;
    public String direction ;
}
