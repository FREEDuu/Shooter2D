package Model;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import manager.Utils;
import View.PanelGame;
import java.awt.Graphics2D;

public class Entity {

    // classe generica per implementare poi il player ed i mob
    // all'interno ci saranno funzioni generiche come paintComponent ecc

    public int x,y,speed, imageCounter, imageNumber, life, rand, lifeW;
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

    public void onHit(){
        gp.SoundHitMonster.play();
    }

    public void drawE(Graphics2D g2){
        BufferedImage image = down[0];
        int screenX, screenY;

        screenX = x - gp.player.x + gp.player.cameraX;
        screenY = y - gp.player.y + gp.player.cameraY;
        if(x > gp.player.x - gp.player.cameraX - (8 * gp.tileSize ) &&
            x < gp.player.x + gp.player.cameraX + (8 * gp.tileSize) &&
            y > gp.player.y - gp.player.cameraY - ( 8 * gp.tileSize) &&
            y < gp.player.y + gp.player.cameraY + ( 8 * gp.tileSize) ){

                switch (direction) {
                    case "up":
                        if(idle){
                            image = up[0];
                        }
                        else{
                            image = up[imageNumber];
                        }
                        break;
                    case "down":
                        if(idle){
                            image = down[0];
                        }
                        else{
                            image = down[imageNumber];
                        }
                        break;
                    case "right":
                        if(idle){
                            image = left[0];
                        }
                        else{
                            image = left[imageNumber];
                        }
                        break;
                    case "left":
                        image = right[imageNumber];
                        if(idle){
                            image = right[0];
                        }
                        else{
                            image = right[imageNumber];
                        }
                        break;
                }

                g2.drawImage(image, screenX - (gp.tileSize*4), screenY - (gp.tileSize*4), gp.tileSize*8, gp.tileSize*8, null );
                g2.fillRect(screenX  - (gp.tileSize * 4), screenY - (gp.tileSize * 4), HP.width, HP.height);
                g2.drawRect(screenX - (gp.tileSize * 4), screenY - (gp.tileSize * 4), lifeW, HP.height);

            }

    }

    public void retrieveImage(String Path){
        try{
            down[0] = ImageIO.read(new File(Path)).getSubimage(0, 0, 24, 24);
            down[1] = ImageIO.read(new File(Path)).getSubimage(24, 0, 24, 24);
            down[2] = ImageIO.read(new File(Path)).getSubimage(48, 0, 24, 24);
            down[3] = ImageIO.read(new File(Path)).getSubimage(72, 0, 24, 24);
            right[0] = ImageIO.read(new File(Path)).getSubimage(0, 24, 24, 24);
            right[1] = ImageIO.read(new File(Path)).getSubimage(24, 24, 24, 24);
            right[2] = ImageIO.read(new File(Path)).getSubimage(48, 24, 24, 24);
            right[3] = ImageIO.read(new File(Path)).getSubimage(72, 24, 24, 24);
            left[0] = ImageIO.read(new File(Path)).getSubimage(0, 48, 24, 24);
            left[1] = ImageIO.read(new File(Path)).getSubimage(24, 48, 24, 24);
            left[2] = ImageIO.read(new File(Path)).getSubimage(48, 48, 24, 24);
            left[3] = ImageIO.read(new File(Path)).getSubimage(72, 48, 24, 24);
            up[0] = ImageIO.read(new File(Path)).getSubimage(0, 72, 24, 24);
            up[1] = ImageIO.read(new File(Path)).getSubimage(24, 72, 24, 24);
            up[2] = ImageIO.read(new File(Path)).getSubimage(48, 72, 24, 24);
            up[3] =ImageIO.read(new File(Path)).getSubimage(72, 72, 24, 24);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onDeath(){

        this.onLife = false;

        int randPotion = choice.nextInt(7);
        if(randPotion < 4){
            switch (randPotion) {
                case 0:
                    gp.Potions.add(new Potion(gp, x, y, "maxHealtIncrease"));
                    break;
                case 1:
                    gp.Potions.add(new Potion(gp, x, y, "HealthRegen"));
                    break;
                case 2:
                    gp.Potions.add(new Potion(gp, x, y, "SpeedIncrease"));
                    break;
                case 3:
                    gp.Potions.add(new Potion(gp, x, y, "AttackIncrease"));
                    break;
            
                default:
                    break;
            }
        }

        gp.SoundMonsterDeath.play();
    }

    public void checkCollisionEntity(){
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
    }
    public void draw(Graphics2D g2){
        
    }
    public void getSoundHit(){
        gp.SoundHitMonster.play();
    }
    public void playDeath(){
        gp.SoundMonsterDeath.play();

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
            smartMove(pgx, pgy);
        }
        
        checkCollisionEntity();
        if(Math.abs(gp.player.x - this.x) == pgx && pgy == Math.abs(gp.player.y - this.y)){
            blocked = true;
        }
        else{
            blocked = false;
        }
    }

    
    public void smartMove(int pgx, int pgy){
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
}
