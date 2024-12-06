package manager;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import frame.PanelGame;

public class TileManager {
    // classe per la gestione della mappa (insieme di tile)

    PanelGame gp;
    Tile[] tiles = new Tile[32];
    int numTiles = 7;
    int tileLimit = 8;
    int[][] mapTileArrayIndex = new int[100][100];

    public TileManager(PanelGame gp) {
        this.gp = gp;
        this.getImgTiles();
    }

    public void getImgTiles() {
        try {
            for (int i = 0; i < numTiles; i++) {
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(new File("Resources/Environment/Tiles/" + i + ".png"));
            }
            
            tiles[5].collision = true;
            tiles[6].collision = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        for (int i = 0; i < 6; i++) {
        }
    }

    public void loadMap(String mapPath) {

        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                for (int i = 0; i < gp.maxWorldRow; i++) {
                    String line = br.readLine();
                    String nums[] = line.split(" ");

                    for (int j = 0; j < gp.maxWorldCol; j++) {
                        int num = Integer.parseInt(nums[j]);
                        mapTileArrayIndex[i][j] = num;


                    }
                }
            }
            
        } catch (IOException | NumberFormatException e) {
        }
    }

    public boolean limitLeft(int worldX){
        if(worldX > gp.player.x - gp.player.cameraX - (tileLimit * gp.tileSize)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean limitright(int worldX){
        if(worldX < gp.player.x + gp.player.cameraX + (tileLimit * gp.tileSize)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean limitUp(int worldY){
        if(worldY > gp.player.y - gp.player.cameraY - (tileLimit * gp.tileSize)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean limitDown(int worldY){
        if(worldY < gp.player.y + gp.player.cameraY + (tileLimit * gp.tileSize)){
            return true;
        }
        else{
            return false;
        }
    }

    public void draw(Graphics2D g2, String map) {
        loadMap(map);

        int screenY, screenX, worldX, worldY;
        // i is for y-pos and j is for x-pos

        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                worldX = j * gp.tileSize * 4;
                worldY = i * gp.tileSize * 4;

                screenX = worldX - gp.player.x + gp.player.cameraX;
                screenY = worldY - gp.player.y + gp.player.cameraY;
                if ( limitLeft(worldX) && limitright(worldX) &&
                    limitUp(worldY) && limitDown(worldY)) {
                        
                    g2.drawImage(tiles[mapTileArrayIndex[i][j]].image, screenX, screenY, gp.tileSize * 4,
                            gp.tileSize * 4, null);
                }

            }
        }
        /*
         * 
         * //for(int i=0; i<gp.maxWorldRow; i++){
         * for(int j=0; j<gp.maxWorldCol; j++){
         * int worldX = j * gp.tileSize;
         * int worldY = i * gp.tileSize;
         * int screenX = worldX - gp.player.worldX + gp.player.screenX;
         * int screenY = worldY - gp.player.worldY + gp.player.screenY;
         * 
         * if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
         * worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
         * worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
         * worldY < gp.player.worldY + gp.player.screenY + gp.tileSize ){
         * 
         * 
         * }
         * }
         * }
         * 
         */
    }
}
