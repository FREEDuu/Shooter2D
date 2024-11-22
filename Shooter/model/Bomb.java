package model;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import manager.Utils;
import view.PanelGame;

public class Bomb extends Projectile{

    boolean expire;
    BufferedImage[] images;
    BufferedImage img;
    int counter ,counterImg, solidAreaDefaultX, solidAreaDefaultY;

    public Bomb(PanelGame gp, int xCoord, int Ycoord){
        this.gp = gp;
        this.speed = 0;
        this.counter = 0;
        this.counterImg = 0;
        this.damage = gp.bombdmg;
        this.x = xCoord;
        this.expire = false;
        this.y = Ycoord;
        this.hitBox = new Rectangle(-(gp.tileSize * 9),-(9 * gp.tileSize), gp.tileSize * 25, gp.tileSize * 25);
        this.solidAreaDefaultX = hitBox.x;
        this.solidAreaDefaultY = hitBox.y;
        this.getBombImg();
    }

    public void getBombImg() {

        this.images = new BufferedImage[6];

        try{
            images[0] = ImageIO.read(new File("Resources/Bomb/fall.png"));
            images[1] = ImageIO.read(new File("Resources//Bomb/jump.png"));
            images[2] = ImageIO.read(new File("Resources//Bomb/idle.png")).getSubimage(0,0 ,64 ,64 );
            images[3] = ImageIO.read(new File("Resources/Bomb/explode.png")).getSubimage(0,0 ,64 ,64 );
            images[4] = ImageIO.read(new File("Resources/Bomb/explode.png")).getSubimage(64,0 ,64 ,64 );
            images[5] = ImageIO.read(new File("Resources/Bomb/explode.png")).getSubimage(128,0 ,64 ,64 );
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

        g2.drawImage(img, screenX - (gp.tileSize*4) , screenY - (gp.tileSize*4), gp.tileSize*8, gp.tileSize*8, null);
    }

    public void onDeath(){
        Utils.makeExplosion(this, gp.enemies);
    }

}
