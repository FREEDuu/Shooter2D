import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    // classe generica per implementare poi il player ed i mob
    // all'interno ci saranno funzioni generiche come paintComponent ecc

    public int x,y,speed, imageCounter, imageNumber ;
    public BufferedImage[] up, down, left, right ;
    public Rectangle hitBox;
    public String direction ;
    public boolean idle, collision;
}
