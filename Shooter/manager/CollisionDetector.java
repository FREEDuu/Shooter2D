package manager;
import java.util.List;

import entities.Entity;
import entities.Projectile;
import frame.PanelGame;

public class CollisionDetector {

    PanelGame pg;

    public CollisionDetector(PanelGame pg) {
        this.pg = pg;
    }

    public void checkTile(Entity entity) {

        entity.hitBox.x = entity.solidAreaDefaultX;
        entity.hitBox.y = entity.solidAreaDefaultY;

        int leftWorldX = entity.x + entity.hitBox.x;
        int rightWorldX = entity.x + entity.hitBox.x + entity.hitBox.width;
        int topWorldY = entity.y + entity.hitBox.y;
        int bottomWorldY = entity.y + entity.hitBox.y + entity.hitBox.height;

        int leftCol = leftWorldX / (pg.tileSize * 4);
        int rightCol = rightWorldX / (pg.tileSize * 4);
        int topRow = topWorldY / (pg.tileSize * 4);
        int bottomRow = bottomWorldY / (pg.tileSize * 4);

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":

                topRow = (topWorldY - entity.speed) / (pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileArrayIndex[topRow][leftCol];
                tileNum2 = pg.tileM.mapTileArrayIndex[topRow][rightCol];
                if (pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
            case "down":
                bottomRow = (bottomWorldY + entity.speed) / (pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileArrayIndex[bottomRow][leftCol];
                tileNum2 = pg.tileM.mapTileArrayIndex[bottomRow][rightCol];
                if (pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
            case "left":
                leftCol = (leftWorldX - entity.speed) / (pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileArrayIndex[topRow][leftCol];
                tileNum2 = pg.tileM.mapTileArrayIndex[bottomRow][leftCol];
                if (pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
            case "right":
                rightCol = (rightWorldX + entity.speed) / (pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileArrayIndex[topRow][rightCol];
                tileNum2 = pg.tileM.mapTileArrayIndex[bottomRow][rightCol];
                if (pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;


        }
    }

    public static int checkEntity(Entity entity, List<Projectile> target) {

        int index = 999999;
        Entity element;
        for (int i = 0; i < target.size(); i++) {
            element = target.get(i);
            if (element != null && entity != null && element != entity) {
                // imposta le posizioni delle aree dei rettangoli "come sono nello spazio" (e
                // poi reset)
                
                entity.hitBox.x = entity.hitBox.x + entity.x;
                entity.hitBox.y = entity.hitBox.y + entity.y;

                element.hitBox.x = element.hitBox.x + element.x;
                element.hitBox.y = element.hitBox.y + element.y;

                if (entity.hitBox.intersects(element.hitBox)) {
                    return i;
                    }
                // restore dafaults
                entity.hitBox.x = entity.solidAreaDefaultX;
                entity.hitBox.y = entity.solidAreaDefaultY;
                element.hitBox.x = element.solidAreaDefaultX;
                element.hitBox.y = element.solidAreaDefaultY;

            }
        }
        return index;
    }

    public static void checkEnemy(Entity entity, Entity[] target) {

        Entity element;
        for (int i = 0; i < target.length; i++) {
            element = target[i];
            if (element != null && entity != null) {
                // imposta le posizioni delle aree dei rettangoli "come sono nello spazio" (e
                // poi reset)

                entity.hitBox.x = entity.hitBox.x + entity.x;
                entity.hitBox.y = entity.hitBox.y + entity.y;

                element.hitBox.x = element.hitBox.x + element.x;
                element.hitBox.y = element.hitBox.y + element.y;

                estimateCollision(entity);

                if(entity.hitBox.intersects(element.hitBox)) {
                    handleCollision(element, entity);
                }
            
                // restore dafaults
                entity.hitBox.x = entity.solidAreaDefaultX;
                entity.hitBox.y = entity.solidAreaDefaultY;
                element.hitBox.x = element.solidAreaDefaultX;
                element.hitBox.y = element.solidAreaDefaultY;
            }
        }
    }
        

    

    public static void checkEnemy(Entity entity, List<Entity> target) {

        Entity element;
        for (int i = 0; i < target.size(); i++) {
            element = target.get(i);
            if (element != null && entity != null) {
                // imposta le posizioni delle aree dei rettangoli "come sono nello spazio" (e
                // poi reset)

                entity.hitBox.x = entity.hitBox.x + entity.x;
                entity.hitBox.y = entity.hitBox.y + entity.y;

                element.hitBox.x = element.hitBox.x + element.x;
                element.hitBox.y = element.hitBox.y + element.y;

                estimateCollision(entity);
                if (entity.hitBox.intersects(element.hitBox)) {
                    if (element != entity) {
                        element.onDeath();
                        target.remove(i);
                    }
                }
                // restore dafaults
                entity.hitBox.x = entity.solidAreaDefaultX;
                entity.hitBox.y = entity.solidAreaDefaultY;
                element.hitBox.x = element.solidAreaDefaultX;
                element.hitBox.y = element.solidAreaDefaultY;

            }
        }

    }

    public static void estimateCollision(Entity entity){
        if (entity.speed != 0) {
            switch (entity.direction) {
                case "up":
                    entity.hitBox.y -= entity.speed;
                    break;
                case "down":
                    entity.hitBox.y += entity.speed;
                    break;
                case "left":
                    entity.hitBox.x -= entity.speed;
                    break;
                case "right":
                    entity.hitBox.x += entity.speed;
                    break;
            }
        }
    }

    public static void handleCollision(Entity element, Entity entity){
        if (element != entity && entity.speed != 0 && element.speed != 0) {
            entity.collision = true;
            if (entity.HP.width - element.damage > 0) {
                entity.HP.width -= element.damage;
                makeKnockback(entity, element);
            } else {
                entity.onDeath();
            }
             
        }
        else{
            if (element.HP.width - entity.damage > 0) {
                element.HP.width -= entity.damage;
                element.onHit();
            } else {
                element.onDeath();
            }
        }
    }

    public static void makeKnockback(Entity entity, Entity element){
        switch (entity.direction) {
            case "up":
                element.y -= entity.speed;
                break;
            case "down":
                element.y += entity.speed;
                break;
            case "left":
                element.x -= entity.speed;
                break;
            case "right":
                element.x += entity.speed;
                break;
        }
    }

}
