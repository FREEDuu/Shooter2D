package Model;
import java.io.File;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import View.PanelGame;

public class Skeletron extends Entity{

    Random choice = new Random();
    int rand, lifeW;

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

        try{
            String Path;
                Path = "Resources/16x16/dragon.png";
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

    public void draw(Graphics2D g2){
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

    public void update(){
     updateE();   
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
    }
}
