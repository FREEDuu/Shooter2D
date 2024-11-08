package Model;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Controller.Utils;
import View.PanelGame;

import java.awt.Graphics2D;

public class Entity {

    // classe generica per implementare poi il player ed i mob
    // all'interno ci saranno funzioni generiche come paintComponent ecc

    public int x,y,speed, imageCounter, imageNumber, life, rand;
    public BufferedImage[] up, down, left, right ;
    public Rectangle hitBox, HP;
    public BufferedImage img;
    Random choice = new Random();
    public String direction , name;
    public int solidAreaDefaultX, solidAreaDefaultY, defaultSpeed, knockbackCounter, damage;
    public boolean idle, collision, knockback, onLife;
    public boolean blocked = false;
    public boolean blocked2 = false;
    public PanelGame gp;

    public void onDeath(){};
    public void knockback(Entity Collider){
        Collider.direction = this.direction;
        Collider.speed += 12;
        Collider.knockback = true;
    }
    public void draw(Graphics2D g2){
        
    }
    public void getSoundHit(){
        gp.SoundM.PlaySoundEffect(4);
    }
    public void playDeath(){
        gp.SoundM.PlaySoundEffect(5);

    }
    public void updateE(){
        int pgx = Math.abs(gp.player.x - this.x);
        int pgy = Math.abs(gp.player.y - this.y);
        
        imageCounter++;
        if(imageCounter > 15){
            imageNumber++;
            imageNumber = imageNumber % 4;
            imageCounter = 0;
            rand = choice.nextInt(4);
            

            if(pgy > pgx){
                if (!blocked) {
                    if(this.y > gp.player.y){
                        direction = "up";
                    }
                    else{
                        direction = "down";
                    }
                }
                else{
                    if(this.x > gp.player.x){
                        direction = "left";
                    }else{
                        direction = "right";
                    }
                }
            }
            else{
                if (!blocked) {
                    if(this.x > gp.player.x){
                        direction = "left";
                    }else{
                        direction = "right";
                    } 
                }
                else{
                    if(this.y > gp.player.y){
                        direction = "up";
                    }
                    else{
                        direction = "down";
                    }
                }
            }
        }
        Utils.checkCollisionPlayerEnemy(gp.player, gp.enemies, false);
        gp.cDetector.checkTile(this);
        if(collision == false){
        switch(direction){
            case "up": y -= speed; break;
            case "down": y += speed; break;
            case "left": x -= speed; break;
            case "right": x += speed; break;
        }
        }
        collision = false;
        idle = false;
        if(Math.abs(gp.player.x - this.x) == pgx && pgy == Math.abs(gp.player.y - this.y)){
            blocked = true;
        }
        else{
            blocked = false;
        }
    }
}
