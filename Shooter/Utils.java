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

    public static void checkCollisionBulletsEnemy(Entity[] enemy, List<Projectile> bull, int damage){
        int indice = 0;
        for(int i = 0; i < enemy.length; i++){
            indice = CollisionDetector.checkEntity(enemy[i], bull);
            
            if(indice != 999999){
                bull.remove(indice);
                if(enemy[i].HP.width - damage > 0){
                    enemy[i].HP.width -= damage;
                }
                else{
                    enemy[i] = null;
                }
                
            }
        }
    }

    public static void makeExplotion(Entity bomb, Entity[][] enemy){
        for(int i = 0; i < enemy.length; i++){   
            CollisionDetector.checkEnemy(bomb, enemy[i], false);
            bomb.collision = true;
        }

        
    }

    public static void checkCollisionPlayerEnemy(Player player, Entity[][] enemy, boolean CallPlayer){
        for(int i = 0; i < enemy.length; i++){
            CollisionDetector.checkEnemy(player, enemy[i], CallPlayer);
        }

    }   

    public static void checkWhiteBulletPlayer(Entity entity, List<Projectile> Wbullets){
        int indice = 0;
        indice = CollisionDetector.checkEntity(entity, Wbullets);
        if(indice != 999999){
            if(entity.HP.width - 0 > 0){
                entity.HP.width -= 0;
            }
            else{
                entity.onDeath();
            }
            Wbullets.set(indice, null);
        }
        
    }
    public static void onLifeEnemy(Entity [] [] enemy){
        for(int i = 0; i < enemy.length; i++){
            for(int j = 0; j < enemy[i].length; j++){
                if(enemy[i][j] != null && !enemy[i][j].onLife){
                    enemy[i][j] = null;
                }
            }
        }
    }
}

