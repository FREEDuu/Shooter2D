import java.awt.Graphics2D;
import java.awt.*;

public class Player extends Entity{

    PanelGame pg;
    ControllerKey controllerK;

    public Player(PanelGame pg, ControllerKey controllerK){
        this.pg = pg;
        this.controllerK = controllerK;
        this.SetDefault();
    }

    public void SetDefault(){
        this.x = 150;
        this.y = 150;
        this.speed = 10;
    }

    public void update(){
        if(controllerK.up == true){
            y -= speed;
        }
        if(controllerK.down == true){
            y += speed;
        }
        if(controllerK.left == true){
            x -= speed;
        }
        if(controllerK.right == true){
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, 100, 100);
    }
}
