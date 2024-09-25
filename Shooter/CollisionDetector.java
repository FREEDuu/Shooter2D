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
    public int checkEntity(Entity entity, Entity target) {
        
        int index = 1000;
        
        //imposta speed a entity.speed normalemente, aumenta se attacco
        int reach = entity.speed;

                
                //imposta le posizioni delle aree dei rettangoli "come sono nello spazio" (e poi reset)
                
            entity.hitBox.x = entity.hitBox.x + entity.x;    
            entity.hitBox.y = entity.hitBox.y + entity.y;
            
            target.hitBox.x = target.hitBox.x + target.x;
            target.hitBox.y = target.hitBox.y + target.y;
                
                
            if( entity.hitBox.intersects(target.hitBox)) {
                if( target != entity){
                    entity.collision = true;
                    }
                }
            switch(entity.direction) {
                    //if the player is attacking we offset his solid area to the weapon and check collision
                    case "up": entity.hitBox.y -= reach; break;
                    case "down": entity.hitBox.y += reach; break;
                    case "left": entity.hitBox.x -= reach; break;
                    case "right": entity.hitBox.x += reach;  break;
                }
                
                //restore dafaults
            entity.hitBox.x = entity.solidAreaDefaultX;
            entity.hitBox.y = entity.solidAreaDefaultY;
            target.hitBox.x = target.solidAreaDefaultX;
            target.hitBox.y = target.solidAreaDefaultY;

            return index;
            }


}

    