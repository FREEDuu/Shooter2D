import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bullet extends Projectile{
    PanelGame pg;
    BufferedImage img;

    public Bullet(PanelGame pg, int x, int y){
        this.pg = pg; 
        this.name = "Bullet";
        this.speed = 5;
        this.cost = 3;
        this.damage = 2;
        this.getBulletImg();
        this.x = x;
        this.y = y;
}

    public void getBulletImg() {


        try{
            img = ImageIO.read(new File("Shooter/images/projectile/Bullet.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(img, x, y, pg.tileSize, pg.tileSize, null);
    }
}