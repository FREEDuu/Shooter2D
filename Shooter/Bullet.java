import java.io.File;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Bullet extends Projectile{

    PanelGame gp;
    BufferedImage img;
    Random rand = new Random();
    int bulletx, bullety;
    boolean PlayerShoot;

    public Bullet(PanelGame pg, int x, int y, boolean ShootbyPlayer, int damage){
        this.gp = pg; 
        this.damage = damage;
        this.name = "Bullet";
        this.cost = 3;
        this.PlayerShoot = ShootbyPlayer;
        
        this.getBulletImg();
        if(ShootbyPlayer){
            this.x = pg.player.x - (img.getWidth()/2)*2 + ( pg.tileSize*8/2);
            this.y = pg.player.y - (img.getHeight()/2)*2 + (pg.tileSize*8/2);
            this.angle = Utils.getMouseAngle(pg.width/2, pg.height/2, x, y);
            this.speed = 40;
        }
        else{
            this.x = x;
            this.y = y;
            this.angle = Utils.getMouseAngle(this.x, this.y, pg.player.x, pg.player.y);
            this.speed = 20;

        }
  
        this.hitBox = new Rectangle(gp.tileSize,gp.tileSize,gp.tileSize*2,gp.tileSize*2);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
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

    public void update(){
        x +=  AddTrajectoriesX(angle);
        y +=  AddTrajectoriesY(angle);        
    }

    public void draw(Graphics2D g2){

        int screenX, screenY;

        screenX = x - gp.player.x + gp.player.cameraX;
        screenY = y - gp.player.y + gp.player.cameraY;

        g2.drawImage(img, screenX , screenY, gp.tileSize*3, gp.tileSize*3, null);
        g2.drawRect(screenX + hitBox.x , screenY + hitBox.y, hitBox.width, hitBox.height);

    }
}