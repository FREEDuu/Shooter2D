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
        this.getPgImgs();

    }

    public void getPgImgs(){
        up = new BufferedImage[4];
        down = new BufferedImage[4];
        left = new BufferedImage[4];
        right = new BufferedImage[4];

        try{
            up[0] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(0, 0, 32, 32);
            up[1] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(32, 0, 32, 32);
            up[2] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(64, 0, 32, 32);
            up[3] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/up.png")).getSubimage(96, 0, 32, 32);
            right[0] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(0, 0, 32, 32);
            right[1] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(32, 0, 32, 32);
            right[2] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(64, 0, 32, 32);
            right[3] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/right.png")).getSubimage(96, 0, 32, 32);
            left[0] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(0, 0, 32, 32);
            left[1] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(32, 0, 32, 32);
            left[2] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(64, 0, 32, 32);
            left[3] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/left.png")).getSubimage(96, 0, 32, 32);
            down[0] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(0, 0, 32, 32);
            down[1] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(32, 0, 32, 32);
            down[2] = ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(64, 0, 32, 32);
            down[3] =ImageIO.read(new File("/home/fred/Desktop/etc/Shooter2D/Shooter/images/player/walk/down.png")).getSubimage(96, 0, 32, 32);
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
        this.imageCounter = 0;
        this.imageNumber = 0;
    }

    public void update(){
        if(controllerK.up || controllerK.down || controllerK.left || controllerK.right){
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
            imageCounter++;
            if(imageCounter > 15){
                imageNumber++;
                imageNumber = imageNumber % 4;
                imageCounter = 0;
            }
            idle = false;
        }
        else{
            idle = true;
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

        g2.drawImage(image, x, y, pg.tileSize*8, pg.tileSize*8, null);
        
    }
}
