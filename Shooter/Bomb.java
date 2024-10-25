import java.io.File;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Bomb extends Projectile{

    PanelGame pg;
    boolean expire;
    BufferedImage[] images;
    BufferedImage img;
    int counter ,counterImg, solidAreaDefaultX, solidAreaDefaultY;

    public Bomb(PanelGame pg, int xCoord, int Ycoord){
        this.pg = pg;
        this.speed = 0;
        this.counter = 0;
        this.counterImg = 0;
        this.damage = 90;
        this.x = xCoord;
        this.expire = false;
        this.y = Ycoord;
        this.hitBox = new Rectangle(-(pg.tileSize * 9),-(9 * pg.tileSize), pg.tileSize * 25, pg.tileSize * 25);
        this.solidAreaDefaultX = hitBox.x;
        this.solidAreaDefaultY = hitBox.y;
        this.getBombImg();
    }

    public void getBombImg() {

        this.images = new BufferedImage[6];

        try{
            images[0] = ImageIO.read(new File("Shooter/images/Bomb/fall.png"));
            images[1] = ImageIO.read(new File("Shooter/images/Bomb/jump.png"));
            images[2] = ImageIO.read(new File("Shooter/images/Bomb/idle.png")).getSubimage(0,0 ,64 ,64 );
            images[3] = ImageIO.read(new File("Shooter/images/Bomb/explode.png")).getSubimage(0,0 ,64 ,64 );
            images[4] = ImageIO.read(new File("Shooter/images/Bomb/explode.png")).getSubimage(64,0 ,64 ,64 );
            images[5] = ImageIO.read(new File("Shooter/images/Bomb/explode.png")).getSubimage(128,0 ,64 ,64 );
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.img = images[0];
    }

    public void update(){
        counter++;
        if(counter % 15 == 0){
            counterImg++;
            if(counterImg < 6){
                img = images[counterImg];
            }
            else{
                onDeath();
            }
        }
    }

    public void draw(Graphics2D g2){

        int screenX, screenY;

        screenX = x - pg.player.x + pg.player.cameraX;
        screenY = y - pg.player.y + pg.player.cameraY;

        g2.drawImage(img, screenX , screenY, pg.tileSize*8, pg.tileSize*8, null);
        g2.drawRect(screenX + hitBox.x , screenY + hitBox.y, hitBox.width, hitBox.height);

    }

    public void onDeath(){
        pg.SoundM.PlaySoundEffect(2);
        Utils.makeExplosion(this, pg.enemies);

    }

}
