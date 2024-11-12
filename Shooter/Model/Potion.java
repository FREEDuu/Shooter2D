package Model;
import java.io.File;
import java.util.Random;
import java.awt.*;
import javax.imageio.ImageIO;

import View.PanelGame;

public class Potion extends Entity{

    Random choice = new Random();
    int rand, lifeW;

    public Potion(PanelGame gp, int x , int y, String name){

        this.name = name;
        this.onLife = true;
        this.damage = 50;
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle(0 ,0, gp.tileSize * 2, gp.tileSize * 2);
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
        this.gp = gp;
        this.getPotionImg();
        
    }

    public void getPotionImg(){

        try{
            if(name.equals("maxHealtIncrease")){
                String Path;
                Path = "Resources/potion_red.png";
                img = ImageIO.read(new File(Path));
            }
            else if(name.equals("HealthRegen")){
                String Path;
                Path = "Resources/heart_full.png";
                img = ImageIO.read(new File(Path));
            }
            else if(name.equals("SpeedIncrease")){
                String Path;
                Path = "Resources/manacrystal_full.png";
                img = ImageIO.read(new File(Path));
            }
            else if(name.equals("AttackIncrease")){
                String Path;
                Path = "Resources/sword_normal.png";
                img = ImageIO.read(new File(Path));
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void draw(Graphics2D g2){

        int screenX, screenY;

        screenX = x - gp.player.x + gp.player.cameraX;
        screenY = y - gp.player.y + gp.player.cameraY;
        
        g2.drawImage(img, screenX , screenY, gp.tileSize*2, gp.tileSize*2, null );
        g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);


    }


    public void onDeath(){
        if (name.equals("HealthRegen")) {
            if (gp.player.HP.width + 50 <= gp.player.maxHealth) {
                gp.player.HP.width += 50;
           }
        }
        if (name.equals("maxHealtIncrease")) {
            gp.player.maxHealth += 50;
        }
        if (name.equals("SpeedIncrease")) {
            gp.player.SpeedIncrease();
        }
        if (name.equals("AttackIncrease")) {
            gp.player.DamageIncrease();
        }
        gp.SoundPotion.play();
    }

    
}
