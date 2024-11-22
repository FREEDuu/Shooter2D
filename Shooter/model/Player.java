package model;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import manager.ControllerKey;
import manager.Utils;
import manager.mouseController;
import view.PanelGame;

public class Player extends Entity{

    public int cameraX, cameraY, damage, maxHealth, speedCounter;
    ControllerKey controllerK;
    mouseController mouseC;
    boolean reload, reloadBomb;
    int reloadCounter, reloadBombCounter;
    Rectangle RectReload, rectReloadBomb;
    int wLife;

    public Player(PanelGame gp, ControllerKey controllerK, mouseController mouseC){
        this.gp = gp;
        this.RectReload = new Rectangle(0 ,0, 350, 50);
        this.rectReloadBomb = new Rectangle(0 ,0, 100, 50);
        this.controllerK = controllerK;
        this.mouseC = mouseC;
        this.reloadCounter = 0;
        this.getPgImgs();
        this.SetDefault();
        this.hitBox = new Rectangle(-(gp.tileSize * 2)/2, -(4 * gp.tileSize)/2, gp.tileSize * 2, gp.tileSize * 4);
        cameraY = ( gp.height / 2);
        cameraX = ( gp.width / 2);
        this.reload = true;
        this.HP = new Rectangle(0,0, 500, 50);
        this.maxHealth = HP.width;
        this.solidAreaDefaultX = this.hitBox.x;
        this.solidAreaDefaultY = this.hitBox.y;
        this.wLife = HP.width;
    }

    public void getPgImgs(){
        up = new BufferedImage[4];
        down = new BufferedImage[4];
        left = new BufferedImage[4];
        right = new BufferedImage[4];

        try{
            String Path;
            if (gp.playerChoice.equals("babypunk")) {
                this.name = "babypunk";
                Path = "Resources/16x16/babypunk.png";
                down[0] = ImageIO.read(new File(Path)).getSubimage(0, 0, 24, 24);
                down[1] = ImageIO.read(new File(Path)).getSubimage(24, 0, 24, 24);
                down[2] = ImageIO.read(new File(Path)).getSubimage(48, 0, 24, 24);
                down[3] = ImageIO.read(new File(Path)).getSubimage(72, 0, 24, 24);
                left[0] = ImageIO.read(new File(Path)).getSubimage(0, 24, 24, 24);
                left[1] = ImageIO.read(new File(Path)).getSubimage(24, 24, 24, 24);
                left[2] = ImageIO.read(new File(Path)).getSubimage(48, 24, 24, 24);
                left[3] = ImageIO.read(new File(Path)).getSubimage(72, 24, 24, 24);
                right[0] = ImageIO.read(new File(Path)).getSubimage(0, 48, 24, 24);
                right[1] = ImageIO.read(new File(Path)).getSubimage(24, 48, 24, 24);
                right[2] = ImageIO.read(new File(Path)).getSubimage(48, 48, 24, 24);
                right[3] = ImageIO.read(new File(Path)).getSubimage(72, 48, 24, 24);
                up[0] = ImageIO.read(new File(Path)).getSubimage(0, 72, 24, 24);
                up[1] = ImageIO.read(new File(Path)).getSubimage(24, 72, 24, 24);
                up[2] = ImageIO.read(new File(Path)).getSubimage(48, 72, 24, 24);
                up[3] =ImageIO.read(new File(Path)).getSubimage(72, 72, 24, 24);
            }
            
            else if(gp.playerChoice.equals("queen")){
                this.name = "queen";
                Path = "Resources/16x16/queen.png";
                down[0] = ImageIO.read(new File(Path)).getSubimage(0, 0, 24, 24);
                down[1] = ImageIO.read(new File(Path)).getSubimage(24, 0, 24, 24);
                down[2] = ImageIO.read(new File(Path)).getSubimage(48, 0, 24, 24);
                down[3] = ImageIO.read(new File(Path)).getSubimage(72, 0, 24, 24);
                left[0] = ImageIO.read(new File(Path)).getSubimage(0, 24, 24, 24);
                left[1] = ImageIO.read(new File(Path)).getSubimage(24, 24, 24, 24);
                left[2] = ImageIO.read(new File(Path)).getSubimage(48, 24, 24, 24);
                left[3] = ImageIO.read(new File(Path)).getSubimage(72, 24, 24, 24);
                right[0] = ImageIO.read(new File(Path)).getSubimage(0, 48, 24, 24);
                right[1] = ImageIO.read(new File(Path)).getSubimage(24, 48, 24, 24);
                right[2] = ImageIO.read(new File(Path)).getSubimage(48, 48, 24, 24);
                right[3] = ImageIO.read(new File(Path)).getSubimage(72, 48, 24, 24);
                up[0] = ImageIO.read(new File(Path)).getSubimage(0, 72, 24, 24);
                up[1] = ImageIO.read(new File(Path)).getSubimage(24, 72, 24, 24);
                up[2] = ImageIO.read(new File(Path)).getSubimage(48, 72, 24, 24);
                up[3] =ImageIO.read(new File(Path)).getSubimage(72, 72, 24, 24);
            }
            else if(gp.playerChoice.equals("erbiondo")){
                this.name = "erbiondo";
                Path = "Resources/16x16/erbiondo.png";
                down[0] = ImageIO.read(new File(Path)).getSubimage(0, 0, 24, 24);
                down[1] = ImageIO.read(new File(Path)).getSubimage(24, 0, 24, 24);
                down[2] = ImageIO.read(new File(Path)).getSubimage(48, 0, 24, 24);
                down[3] = ImageIO.read(new File(Path)).getSubimage(72, 0, 24, 24);
                left[0] = ImageIO.read(new File(Path)).getSubimage(0, 24, 24, 24);
                left[1] = ImageIO.read(new File(Path)).getSubimage(24, 24, 24, 24);
                left[2] = ImageIO.read(new File(Path)).getSubimage(48, 24, 24, 24);
                left[3] = ImageIO.read(new File(Path)).getSubimage(72, 24, 24, 24);
                right[0] = ImageIO.read(new File(Path)).getSubimage(0, 48, 24, 24);
                right[1] = ImageIO.read(new File(Path)).getSubimage(24, 48, 24, 24);
                right[2] = ImageIO.read(new File(Path)).getSubimage(48, 48, 24, 24);
                right[3] = ImageIO.read(new File(Path)).getSubimage(72, 48, 24, 24);
                up[0] = ImageIO.read(new File(Path)).getSubimage(0, 72, 24, 24);
                up[1] = ImageIO.read(new File(Path)).getSubimage(24, 72, 24, 24);
                up[2] = ImageIO.read(new File(Path)).getSubimage(48, 72, 24, 24);
                up[3] =ImageIO.read(new File(Path)).getSubimage(72, 72, 24, 24);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void shoot(){
        
        gp.bullets.add(new Bullet(gp, MouseInfo.getPointerInfo().getLocation().x , MouseInfo.getPointerInfo().getLocation().y, true, 50));
        if(this.name.equals("babypunk")){
            gp.bullets.add(new Bullet(gp, MouseInfo.getPointerInfo().getLocation().x+2 , MouseInfo.getPointerInfo().getLocation().y+2, true, 50));
        }

        gp.SoundShootPlayer.play();
        
    }
    public void shootBomb(){
        gp.BombPlayerList.add(new Bomb(gp, gp.player.x, gp.player.y));
        if(this.name.equals("queen")){
            gp.BombPlayerList.add(new Bomb(gp, gp.player.x+5, gp.player.y+5));
        }
    }

    public void SetDefault(){
        this.x = 2600;
        this.y = 2600;
        this.speed = 10;
        speedCounter = 0;
        if(this.name.equals("erbiondo")){
            this.speed = 15;
            this.damage = 50;
        }
        this.defaultSpeed = speed;
        this.direction = "down";
        this.imageCounter = 0;
        this.imageNumber = 0;
    }

    public void update(){
        if(speed > 10 && speedCounter == 0){
            speed--;
        }
        if(speedCounter > 0){
            speedCounter--;
        }
        reloadCounter++;
        reloadBombCounter++;
        if(RectReload.width < 350){
            RectReload.width += 10;
        }
        if(rectReloadBomb.width < 100){
            rectReloadBomb.width += 2;
        }
        if(reloadCounter > 35){
            reload = true;
            reloadCounter = 0;
        }
        if(reloadBombCounter > 50){
            reloadBomb = true;
            reloadBombCounter = 0;
        }
        if(mouseC.shoot && reload){
            shoot();
            reload = false;
            RectReload.width = 0;
        }
        if(controllerK.bomb && reloadBomb){
            shootBomb();
            reloadBomb = false;
            rectReloadBomb.width = 0;
        }
        if(controllerK.up || controllerK.down || controllerK.left || controllerK.right){
            if(controllerK.up == true){
                direction = "up";
            }
            if(controllerK.down == true){
                direction = "down";
            }
            if(controllerK.left == true){
                direction = "left";
            }
            if(controllerK.right == true){
                direction = "right";
            }
            imageCounter++;
            if(imageCounter > 15){
                imageNumber++;
                imageNumber = imageNumber % 4;
                imageCounter = 0;
            }
            idle = false;
            collision = false;
            gp.cDetector.checkTile(this);
            Utils.checkCollisionPlayerEnemy(this, gp.enemies, true);
            if(collision == false){

                switch(direction){
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
                }
            }
            
            else{
                idle = true;
            }

        
        }
 
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null ;
        
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
            case "left":
                if(idle){
                    image = left[0];
                }
                else{
                    image = left[imageNumber];
                }
                break;
            case "right":
                image = right[imageNumber];
                if(idle){
                    image = right[0];
                }
                else{
                    image = right[imageNumber];
                }
                break;
        }
        g2.drawImage(image, cameraX - (gp.tileSize*4), cameraY - (gp.tileSize*4), gp.tileSize*8, gp.tileSize*8, null);
        g2.setColor(Color.gray);
        g2.fillRect(HP.x + gp.tileSize, HP.y + gp.tileSize*10 , rectReloadBomb.width, rectReloadBomb.height);
        g2.drawRect(HP.x + gp.tileSize, HP.y + gp.tileSize*10 , 100, 50);
        g2.setColor(Color.BLACK);
        g2.fillRect(HP.x + gp.tileSize, HP.y + gp.tileSize*5 , RectReload.width, RectReload.height);
        g2.drawRect(HP.x + gp.tileSize, HP.y + gp.tileSize*5 , 350, 50);
        g2.setColor(Color.red);
        g2.fillRect(HP.x - gp.tileSize, HP.y + gp.tileSize, HP.width, HP.height);
        g2.drawRect(HP.x + gp.tileSize, HP.y + gp.tileSize, maxHealth, HP.height);
    }

    public void onDeath(){
        if (gp.gameState != gp.loseState) {
            if(gp.Lvl == 3){
                gp.SoundBoss.stop();
            }
            else if(gp.Lvl == 2){
                gp.SoundLvl2.stop();
            }
            else{
                gp.SoundWhilePlay.stop();
            }
            gp.SoundLose.play();
            gp.gameState = gp.loseState;
        }
    }

    public void DamageIncrease(){
        this.damage += 10;
    }
    public void SpeedIncrease(){
        this.speed += 7;
        this.speedCounter = 120;
    }
    public void HealthIncrease(){
        maxHealth += 120;
        HP.width = maxHealth;
    }
    public void BombIncrease(){
        gp.bombdmg += 30;
    }
    public void BothIncrease(){
        gp.bombdmg += 15;
        damage += 5;
    }
    public void getSoundHit(){
        //gp.SoundM.PlaySoundEffect(4);
    }
    public void playDeath(){
        //gp.SoundM.PlaySoundEffect(5);

    }}
