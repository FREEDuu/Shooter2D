import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Bullet extends Projectile{

    PanelGame gp;
    double angle;
    BufferedImage img;
    Random rand = new Random();
    int bulletx, bullety;

    public Bullet(PanelGame pg, int x, int y){
        this.gp = pg; 
        this.name = "Bullet";
        this.speed = 40;
        this.cost = 3;
        this.damage = 2;
        this.angle = Utils.getAngle(pg.width/2, pg.height/2, x, y);
        this.getBulletImg();
        this.x = pg.player.x - (img.getWidth()/2)*2 + ( pg.tileSize*8/2);
        this.y = pg.player.y - (img.getHeight()/2)*2 + (pg.tileSize*8/2);
    }

    public void getBulletImg() {


        try{
            img = ImageIO.read(new File("Shooter/images/projectile/BulletProjectile.png"));
            //img = Utils.rotate(img, 90);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public int AddTrajectoriesX(double angle){

        return (int)Math.floor(this.speed * Math.cos(angle));
    }
    public int AddTrajectoriesY(double angle){

        return (int)Math.floor(this.speed * Math.sin(angle));
    }

    public void update(double angle){
        x +=  AddTrajectoriesX(angle);
        y +=  AddTrajectoriesY(angle);        
    }

    public void draw(Graphics2D g2){

        int screenX, screenY;

        screenX = x - gp.player.x + gp.player.cameraX;
        screenY = y - gp.player.y + gp.player.cameraY;

        g2.drawImage(img, screenX , screenY, img.getWidth()*2, img.getHeight()*2, null);

    }
}