import java.io.File;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Bomb extends Projectile{

    PanelGame pg;
    BufferedImage[] images;
    BufferedImage img;
    int counter ,counterImg;
    boolean explosion;

    public Bomb(PanelGame pg, int xCoord, int Ycoord){
        this.gp = pg;
        this.counter = 0;
        this.counterImg = 0;
        this.damage = 1000;
        this.x = xCoord;
        this.y = Ycoord;
        this.explosion = false;
        this.hitBox = new Rectangle(gp.tileSize,gp.tileSize,gp.tileSize*2,gp.tileSize*2);
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

        screenX = x - gp.player.x + gp.player.cameraX;
        screenY = y - gp.player.y + gp.player.cameraY;

        g2.drawImage(img, screenX , screenY, gp.tileSize*8, gp.tileSize*8, null);
        g2.drawRect(screenX + hitBox.x , screenY + hitBox.y, hitBox.width, hitBox.height);

    }

    public void explode(){

        this.hitBox = new Rectangle(hitBox.x, hitBox.y, 300, 300);

    }

    public void onDeath(){
        explode();
        explosion = true;
        }

}
