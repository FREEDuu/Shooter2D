import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Player extends Entity{

    int cameraX, cameraY;
    ControllerKey controllerK;
    PanelGame pg;
    boolean reload;
    int reloadCounter;

    public Player(PanelGame pg, ControllerKey controllerK){
        this.pg = pg;
        this.controllerK = controllerK;
        this.reloadCounter = 0;
        this.SetDefault();
        this.getPgImgs();
        cameraY = ( pg.height / 2);
        cameraX = ( pg.width / 2) ;
        this.reload = true;
        this.HP = new Rectangle(0,0, 500, 50);
        this.hitBox = new Rectangle((pg.tileSize * 3), (2 * pg.tileSize), pg.tileSize * 2, pg.tileSize * 3);


    }

    public void getPgImgs(){
        up = new BufferedImage[4];
        down = new BufferedImage[4];
        left = new BufferedImage[4];
        right = new BufferedImage[4];

        try{
            String Path;
            if (pg.playerChoice.equals("babypunk")) {
                Path = "Shooter/images/16x16/babypunk.png";
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
            
            else if(pg.playerChoice.equals("queen")){
                Path = "Shooter/images/16x16/queen.png";
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
            else if(pg.playerChoice.equals("erbiondo")){
                Path = "Shooter/images/16x16/erbiondo.png";
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
        pg.bullets.add(new Bullet(pg, MouseInfo.getPointerInfo().getLocation().x , MouseInfo.getPointerInfo().getLocation().y));
    }

    public void SetDefault(){
        this.x = 500;
        this.y = 500;
        this.speed = 10;
        this.direction = "down";
        this.imageCounter = 0;
        this.imageNumber = 0;
    }

    public void update(){
        reloadCounter++;
        if(reloadCounter > 70){
            reload = true;
            reloadCounter = 0;
        }
        if(controllerK.shoot && reload){
            shoot();
            reload = false;
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
            pg.cDetector.checkTile(this);

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

        g2.drawImage(image, cameraX, cameraY, pg.tileSize*8, pg.tileSize*8, null);
        g2.setColor(Color.red);
        g2.drawRect(HP.x, HP.y, HP.width, HP.height);
        g2.fillRect(HP.x +10, HP.y + 10, HP.width, HP.height);
    }
}
