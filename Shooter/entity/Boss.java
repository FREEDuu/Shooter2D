package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

import frame.PanelGame;

public class Boss extends Entity{

    BufferedImage image;
    Random choice = new Random();
    BufferedImage[] up_a, down_a, right_a, left_a;
    int rand, lifeW, pgx, pgy;
    Rectangle hitboxAttack, normalHitbox;
    boolean onRange = false;
    int distanceToAttack;

    public Boss(PanelGame gp){

        this.name = "Boss";
        this.direction = "down";
        this.onLife = true;
        this.damage = 10;
        this.distanceToAttack = 120;
        this.collision = false;
        this.speed = 6;
        this.normalHitbox = new Rectangle(-(gp.tileSize ), -(3* gp.tileSize)/2, gp.tileSize * 2, gp.tileSize * 3);
        this.hitboxAttack = new Rectangle(-(2*gp.tileSize ), -(3* gp.tileSize), gp.tileSize * 4, gp.tileSize * 6);
        this.hitBox = hitboxAttack;
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
        this.life = 10;
        this.gp = gp;
        this.HP = new Rectangle(0,0,350,25);
        this.lifeW = HP.width;
        this.imageCounter = 0;
        this.imageNumber = 0;
        this.getSkImgs();
        
    }

    public void getSkImgs(){
        up = new BufferedImage[2];
        down = new BufferedImage[2];
        left = new BufferedImage[2];
        right = new BufferedImage[2];

        up_a = new BufferedImage[2];
        down_a = new BufferedImage[2];
        left_a = new BufferedImage[2];
        right_a = new BufferedImage[2];


        try{
                down_a[0] = ImageIO.read(new File("Resources/Boss/orc_attack_down_1.png"));
                down_a[1] = ImageIO.read(new File("Resources/Boss/orc_attack_down_2.png"));
                down[0] = ImageIO.read(new File("Resources/Boss/orc_down_1.png"));
                down[1] = ImageIO.read(new File("Resources/Boss/orc_down_2.png"));
                up_a[0] = ImageIO.read(new File("Resources/Boss/orc_attack_up_1.png"));
                up_a[1] = ImageIO.read(new File("Resources/Boss/orc_attack_up_2.png"));
                up[0] = ImageIO.read(new File("Resources/Boss/orc_up_1.png"));
                up[1] = ImageIO.read(new File("Resources/Boss/orc_up_2.png"));               
                left_a[0] = ImageIO.read(new File("Resources/Boss/orc_attack_left_1.png"));
                left_a[1] = ImageIO.read(new File("Resources/Boss/orc_attack_left_2.png"));
                left[0] = ImageIO.read(new File("Resources/Boss/orc_left_1.png"));    
                left[1] = ImageIO.read(new File("Resources/Boss/orc_left_2.png"));                        
                right_a[0] = ImageIO.read(new File("Resources/Boss/orc_attack_right_1.png"));
                right_a[1] = ImageIO.read(new File("Resources/Boss/orc_attack_right_2.png"));
                right[0] = ImageIO.read(new File("Resources/Boss/orc_right_1.png"));
                right[1] = ImageIO.read(new File("Resources/Boss/orc_right_2.png"));
        }   
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        image = down[0];
        int screenX, screenY;
        
        screenX = x - gp.player.x + gp.player.cameraX;
        screenY = y - gp.player.y + gp.player.cameraY;
        if(x > gp.player.x - gp.player.cameraX - (8 * gp.tileSize ) &&
            x < gp.player.x + gp.player.cameraX + (8 * gp.tileSize) &&
            y > gp.player.y - gp.player.cameraY - ( 8 * gp.tileSize) &&
            y < gp.player.y + gp.player.cameraY + ( 8 * gp.tileSize) ){
                checkDistance();
                switch (direction) {
                    case "up":
                        if (onRange) {
                            image = up_a[imageNumber];
                        }
                        else{
                            image = up[imageNumber];
                        }
                        break;
                    case "down":                        
                   
                        if (onRange) {
                            image = down_a[imageNumber];
                        }
                        else{
                            image = down[imageNumber];
                        }                        
                        break;
                    case "right":

                        if (onRange) {
                            image = right_a[imageNumber];
                        }
                        else{
                            image = right[imageNumber];
                        }                        
                        break;
                    case "left":

                        if (onRange) {
                            image = left_a[imageNumber];
                        }
                        else{
                            image = left[imageNumber];
                        }                    
                        break;
                }
                
                if (onRange && (direction.equals("left") || direction.equals("right"))) {
                    g2.drawImage(image, screenX -(gp.tileSize*8), screenY -(gp.tileSize*4), gp.tileSize*16, gp.tileSize*8, null );
                }else if(onRange && (direction.equals("up")|| direction.equals("down"))){
                    g2.drawImage(image, screenX -(gp.tileSize*4), screenY -(gp.tileSize*8), gp.tileSize*8, gp.tileSize*16, null );
                }
                else{
                    g2.drawImage(image, screenX -(gp.tileSize*4), screenY -(gp.tileSize*4), gp.tileSize*8, gp.tileSize*8, null );
                }

                g2.fillRect(screenX -(gp.tileSize*6), screenY -(gp.tileSize*6), HP.width, HP.height);
                g2.drawRect(screenX -(gp.tileSize*6), screenY -(gp.tileSize*6), lifeW, HP.height);
                onRange = false;
            }

    }

    public void update(){

        imageCounter++;
        if(imageCounter > 15){
            imageNumber++;
            imageNumber = imageNumber % 2;
            imageCounter = 0;
            rand = choice.nextInt(4);
            pgx = Math.abs(gp.player.x - this.x);
            pgy = Math.abs(gp.player.y - this.y);
            smartMove(pgx, pgy);
        }
        
    
        checkCollisionEntity();
        collision = false;
        idle = false;

        if(Math.abs(gp.player.x - this.x) == pgx && pgy == Math.abs(gp.player.y - this.y)){
            blocked = true;
        }
        else{
            blocked = false;
        }
           
    }

    public void onDeath(){
        this.onLife = false;
    }
    public void checkDistance(){
        int dx = Math.abs(this.x - this.gp.player.x);
        int dy = Math.abs(this.y - this.gp.player.y);
        if(dx < distanceToAttack || dy < distanceToAttack){
            onRange = true;
        }
        else{
            onRange = false;
        }
    }
}
