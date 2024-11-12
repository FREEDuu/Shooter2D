package Model;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.*;
import View.PanelGame;

public class WhiteMonster extends Entity{

    Random choice = new Random();
    int rand;

    public WhiteMonster(PanelGame gp){

        this.name = "dragonite";
        this.direction = "down";
        this.collision = false;
        this.speed = 3;
        this.onLife = true;
        this.hitBox = new Rectangle(-(gp.tileSize ), -(3 * gp.tileSize)/2, gp.tileSize * 2, gp.tileSize * 5);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
        this.life = 10;
        this.gp = gp;
        this.damage = 10;
        this.HP = new Rectangle(0,0,120,25);
        this.lifeW = HP.width;
        this.imageCounter = 0;
        this.imageNumber = 0;
        this.getSkImgs();
        
    }

    public void shoot(){
        gp.WhiteMonsterbullets.add(new Bullet(gp, this.x , this.y, false, 20));
    }

    public void getSkImgs(){
        up = new BufferedImage[4];
        down = new BufferedImage[4];
        left = new BufferedImage[4];
        right = new BufferedImage[4];
        String Path;
        Path = "Resources/16x16/white.png";
        retrieveImage(Path);
    }

    public void draw(Graphics2D g2){
        drawE(g2);   
       }

    public void update(){
        int pgx = Math.abs(gp.player.x - this.x);
        int pgy = Math.abs(gp.player.y - this.y);
        imageCounter++;
        if(imageCounter > 15){
            
            imageNumber++;
            imageNumber = imageNumber % 4;
            imageCounter = 0;
            rand = choice.nextInt(6);
         //Implementazione di una rudimentale AI che segue il pg
            if(rand == 3){
                this.shoot();
            }
            else{
                
                if(pgy + pgx > 500){
                    smartMove(pgx, pgy);
                }
                else{
                    direction= "idle";
                }
            }
        }
        checkCollisionEntity();
        if(Math.abs(gp.player.x - this.x) == pgx && pgy == Math.abs(gp.player.y - this.y)){
            blocked = true;
        }
        else{
            blocked = false;
        }
        
    }
    
}
