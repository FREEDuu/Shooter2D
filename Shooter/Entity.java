import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    // classe generica per implementare poi il player ed i mob
    // all'interno ci saranno funzioni generiche come paintComponent ecc

    public int x,y,speed, imageCounter, imageNumber, life ;
    public BufferedImage[] up, down, left, right ;
    public Rectangle hitBox, HP;
    public BufferedImage img;
    public String direction , name;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean idle, collision;
    public PanelGame gp;

    public void onDeath(){};
}
