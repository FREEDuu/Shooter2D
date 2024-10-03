import java.util.List;

public class CollisionDetector {

    PanelGame pg;

    public CollisionDetector(PanelGame pg){
        this.pg = pg;
    }

    public void checkTile(Entity entity){

        int leftWorldX = entity.x + entity.hitBox.x;
        int rightWorldX = entity.x + entity.hitBox.x + entity.hitBox.width;
        int topWorldY = entity.y + entity.hitBox.y;
        int bottomWorldY = entity.y + entity.hitBox.y + entity.hitBox.height;
        
        int leftCol = leftWorldX / (pg.tileSize * 4);
        int rightCol = rightWorldX / (pg.tileSize* 4);
        int topRow = topWorldY / (pg.tileSize * 4);
        int bottomRow = bottomWorldY / (pg.tileSize * 4);

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up": 
                topRow = (topWorldY - entity.speed)/ (pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileNum[topRow][leftCol];
                tileNum2 = pg.tileM.mapTileNum[topRow][rightCol];
                if(pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "down": 
                bottomRow = (bottomWorldY + entity.speed)/(pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileNum[bottomRow][leftCol];
                tileNum2 = pg.tileM.mapTileNum[bottomRow][rightCol];
                if(pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "left": 
                leftCol = (leftWorldX - entity.speed)/ (pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileNum[topRow][leftCol];
                tileNum2 = pg.tileM.mapTileNum[bottomRow][leftCol];
                if(pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "right": 
                rightCol = (rightWorldX + entity.speed)/(pg.tileSize * 4);
                tileNum1 = pg.tileM.mapTileNum[topRow][rightCol];
                tileNum2 = pg.tileM.mapTileNum[bottomRow][rightCol];
                if(pg.tileM.tiles[tileNum1].collision == true || pg.tileM.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;

        }
    }
    public static int checkEntity(Entity entity, List<Bullet> target) {
        
        int index = 999999;
        Entity element;
        for(int i=0; i< target.size(); i++ ){
            element = target.get(i);
            if( element != null && entity != null){ 
                    //imposta le posizioni delle aree dei rettangoli "come sono nello spazio" (e poi reset)
                    
                entity.hitBox.x = entity.hitBox.x + entity.x;    
                entity.hitBox.y = entity.hitBox.y + entity.y;
                
                element.hitBox.x = element.hitBox.x + element.x;
                element.hitBox.y = element.hitBox.y + element.y;
                    
                    
                if( entity.hitBox.intersects(element.hitBox)) {
                    if( element != entity){
                        index = i;
                    }
                }                    
                    //restore dafaults
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
        for(int i=0; i< target.length; i++ ){
            element = target[i];
            if( element != null && entity != null){ 
                    //imposta le posizioni delle aree dei rettangoli "come sono nello spazio" (e poi reset)
                    
                entity.hitBox.x = entity.hitBox.x + entity.x;    
                entity.hitBox.y = entity.hitBox.y + entity.y;
                
                element.hitBox.x = element.hitBox.x + element.x;
                element.hitBox.y = element.hitBox.y + element.y;

                switch(entity.direction) {
                    //if the player is attacking we offset his solid area to the weapon and check collision
                    case "up": entity.hitBox.y -= entity.speed; break;
                    case "down": entity.hitBox.y += entity.speed; break;
                    case "left": entity.hitBox.x -= entity.speed; break;
                    case "right": entity.hitBox.x += entity.speed;  break;
                }
                    
                    
                if( entity.hitBox.intersects(element.hitBox)) {
                    if( element != entity){
                        entity.collision = true;
                        element.collision = true;
                        entity.HP.width -= 10;
                    }
                }                    
                    //restore dafaults
                entity.hitBox.x = entity.solidAreaDefaultX;
                entity.hitBox.y = entity.solidAreaDefaultY;
                element.hitBox.x = element.solidAreaDefaultX;
                element.hitBox.y = element.solidAreaDefaultY;

                
            }
        }
        
    }


}

    