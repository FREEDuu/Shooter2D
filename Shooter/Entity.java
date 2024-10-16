import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Entity {

    // classe generica per implementare poi il player ed i mob
    // all'interno ci saranno funzioni generiche come paintComponent ecc

    public int x,y,speed, imageCounter, imageNumber, life ;
    public BufferedImage[] up, down, left, right ;
    public Rectangle hitBox, HP;
    public BufferedImage img;
    public String direction , name;
    public int solidAreaDefaultX, solidAreaDefaultY, defaultSpeed, knockbackCounter, damage;
    public boolean idle, collision, knockback, onLife;
    public PanelGame gp;

    public void onDeath(){};
    public void knockback(Entity Collider){
        Collider.direction = this.direction;
        Collider.speed += 12;
        Collider.knockback = true;
    }
    public void draw(Graphics2D g2){
        
    }
}
