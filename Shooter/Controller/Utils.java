package Controller;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import Model.Entity;
import Model.Player;
import Model.Projectile;

public class Utils {

    public static BufferedImage rotate(BufferedImage bimg, double angle) {
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        Graphics2D graphic = bimg.createGraphics();
        graphic.rotate(angle, w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return bimg;
    }

    public static double getMouseAngle(int midx, int midy, int mousex, int mousey) {
        double dx = mousex - midx;
        double dy = mousey - midy;
        // meglio usare builtin quando possibile
        return Math.atan2(dy, dx);
    }

    public static void checkCollisionBulletsEnemy(Entity[] enemy, List<Projectile> bull) {
        int indice = 0;
        for (int i = 0; i < enemy.length; i++) {
            indice = CollisionDetector.checkEntity(enemy[i], bull);

            if (indice != 999999) {
                if (enemy[i].HP.width - bull.get(indice).damage > 0) {
                    enemy[i].HP.width -= bull.get(indice).damage;
                } else {
                    enemy[i].onDeath();
                    enemy[i] = null;
                }
                bull.remove(indice);
            }
        }
    }

    public static void checkPotions(Entity player, List<Entity> Potions){
        for( int i = 0; i < Potions.size(); i++){
            CollisionDetector.checkEnemy(player, Potions);
        }
    }

    public static void makeExplosion(Entity bomb, Entity[][] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            CollisionDetector.checkEnemy(bomb, enemy[i], false);
            bomb.collision = true;
        }

    }

    public static void checkCollisionPlayerEnemy(Player player, Entity[][] enemy, boolean CallPlayer) {
        for (int i = 0; i < enemy.length; i++) {
            CollisionDetector.checkEnemy(player, enemy[i], CallPlayer);
        }

    }

    public static void checkWhiteBulletPlayer(Entity entity, List<Projectile> Wbullets) {
        int indice = 0;
        indice = CollisionDetector.checkEntity(entity, Wbullets);
        if (indice != 999999) {
            if (entity.HP.width - Wbullets.get(indice).damage > 0) {
                entity.HP.width -= Wbullets.get(indice).damage;
            } else {
                entity.onDeath();
            }
            Wbullets.remove(indice);
        }

    }

    public static void onLifeEnemy(Entity[][] enemy) {
        for (int i = 0; i < enemy.length; i++) {
            for (int j = 0; j < enemy[i].length; j++) {
                if (enemy[i][j] != null && !enemy[i][j].onLife) {
                    enemy[i][j] = null;
                }
            }
        }
    }

    // semplice distanza euclidea
    public static double getPointDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double getEntityDistance(Entity e1, Entity e2) {
        return getPointDistance(e1.x, e1.y, e2.x, e2.y);
    }

    public static boolean isColliding(Entity e1, Entity e2) {
        if (e1.hitBox.intersects(e2.hitBox)) return true;
        else return false; 
    }


}
