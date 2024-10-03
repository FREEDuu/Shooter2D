import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class Utils{

    public static BufferedImage rotate(BufferedImage bimg, double angle) {
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        Graphics2D graphic = bimg.createGraphics();
        graphic.rotate(angle, w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return bimg;
    }

    public static double getAngle(int midx, int midy, int mousex, int mousey){

        
        double difx = Math.abs(midx - mousex);
        double dify = Math.abs(midy - mousey);

        if(midx > mousex && midy > mousey){
            return +Math.atan(dify/difx) - (Math.PI/2 * 2) ;
        }
        else if(midx < mousex && midy > mousey){
            return -Math.atan(dify/difx) ;
        }
        else if(midx > mousex && midy < mousey){
            return (Math.PI/2 * 2) - Math.atan(dify/difx);
        }
        return Math.atan(dify/difx);
    }

    public static void checkCollisionBulletsEnemy(Entity[] enemy, List<Bullet> bullets){
        int indice = 0;
        for(int i = 0; i < enemy.length; i++){
            indice = CollisionDetector.checkEntity(enemy[i], bullets);

            if(indice != 999999){
                if(enemy[i].HP.width - 50 > 0){
                    enemy[i].HP.width -= 50;
                }
                else{
                    enemy[i] = null;
                }
                bullets.set(indice, null);
            }
        }
    }

    public static void checkCollisionPlayerEnemy(Player player, Entity[][] enemy){
        for(int i = 0; i < enemy.length; i++){
            CollisionDetector.checkEnemy(player, enemy[i]);
        }

    }   

    public static void checkWhiteBulletPlayer(Entity entity, List<Bullet> Wbullets){
        int indice = 0;
        indice = CollisionDetector.checkEntity(entity, Wbullets);
        if(indice != 999999){
            if(entity.HP.width - 50 > 0){
                entity.HP.width -= 50;
            }
            else{
                entity.onDeath();
            }
            Wbullets.set(indice, null);
        }
        
    }
}

