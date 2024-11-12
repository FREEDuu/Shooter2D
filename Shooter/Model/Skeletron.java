package Model;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.*;
import View.PanelGame;

public class Skeletron extends Entity{

    Random choice = new Random();
    int rand;

    public Skeletron(PanelGame gp){

        this.name = "mini-drago";
        this.direction = "down";
        this.collision = false;
        this.onLife = true;
        this.damage = 1;
        this.speed = 6;
        this.hitBox = new Rectangle(-(gp.tileSize), -(3 * gp.tileSize)/2, gp.tileSize * 2, gp.tileSize * 5);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
        this.life = 10;
        this.HP = new Rectangle(0,0,120,25);
        this.gp = gp;
        this.imageCounter = 0;
        this.imageNumber = 0;
        this.lifeW = HP.width;
        this.getSkImgs();
        
    }

    public void getSkImgs(){
        up = new BufferedImage[4];
        down = new BufferedImage[4];
        left = new BufferedImage[4];
        right = new BufferedImage[4];
        String Path;
        Path = "Resources/16x16/dragon.png";
        retrieveImage(Path);
    }

    public void draw(Graphics2D g2){
     drawE(g2);   
    }
    public void update(){
     updateE();   
    }
}
