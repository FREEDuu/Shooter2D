import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Player extends Entity{

    PanelGame pg;
    ControllerKey controllerK;

    public Player(PanelGame pg, ControllerKey controllerK){
        this.pg = pg;
        this.controllerK = controllerK;
        this.SetDefault();
    }

    public void getPgImgs(){
        try{
            up1 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(0, 0, 32, 32);
            up2 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(32, 0, 32, 32);
            up3 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(64, 0, 32, 32);
            up4 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(96, 0, 32, 32);
            right1 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(0, 0, 32, 32);
            right2 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(32, 0, 32, 32);
            right3 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(64, 0, 32, 32);
            right4 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(96, 0, 32, 32);
            left1 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(0, 0, 32, 32);
            left2 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(32, 0, 32, 32);
            left3 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(64, 0, 32, 32);
            left4 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(96, 0, 32, 32);
            down1 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(0, 0, 32, 32);
            down2 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(32, 0, 32, 32);
            down3 = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(64, 0, 32, 32);
            down4 =ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(96, 0, 32, 32);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void SetDefault(){
        this.x = 150;
        this.y = 150;
        this.speed = 10;
        this.direction = "down";
    }

    public void update(){
        if(controllerK.up == true){
            y -= speed;
            direction = "up";
        }
        if(controllerK.down == true){
            y += speed;
            direction = "down";

        }
        if(controllerK.left == true){
            x -= speed;
            direction = "left";
        }
        if(controllerK.right == true){
            x += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null ;
        
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                          
        }

        g2.drawImage(image, x, y, 32, 32, null);
        
    }
}
