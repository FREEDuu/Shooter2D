public class CollisioneDetector {

    PanelGame pg;

    public CollisioneDetector(PanelGame pg){
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

}