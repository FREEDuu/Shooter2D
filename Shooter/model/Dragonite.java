package model;
import java.util.Random;

import view.PanelGame;

import java.awt.image.BufferedImage;
import java.awt.*;

public class Dragonite extends Entity{

    Random choice = new Random();
    int rand;

    public Dragonite(PanelGame gp){

        this.name = "dragonite";
        this.direction = "down";
        this.onLife = true;
        this.collision = false;
        this.speed = 8;
        this.hitBox = new Rectangle(-(gp.tileSize ), -(gp.tileSize), gp.tileSize * 2, gp.tileSize * 5);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
        this.life = 4;
        this.damage = 1;
        this.gp = gp;
        this.HP = new Rectangle(0,0,120,25);
        this.lifeW = HP.width;
        this.imageCounter = 0;
        this.imageNumber = 0;
        this.getSkImgs();
        
    }

    public void getSkImgs(){
        up = new BufferedImage[4];
        down = new BufferedImage[4];
        left = new BufferedImage[4];
        right = new BufferedImage[4];
        String Path;
        Path = "Resources/16x16/dragonite.png";
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
            rand = choice.nextInt(3);
        //Implementazione di una rudimentale AI che segue il pg
            if(rand == 2){
                rand = choice.nextInt(4);
                if(rand == 0) {
                    direction = "up";
                }
                else if (rand == 1) {
                    direction = "left";
                }
                else if (rand == 2) {
                    direction = "right";
                }
                else if(rand == 3){
                    direction = "down";
                }
            }
            else{
                
                smartMove(pgx, pgy);
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
