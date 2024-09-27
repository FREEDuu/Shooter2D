import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    //classe per la gestione della mappa (insieme di tile)

    PanelGame gp;
    Tile [] tiles;
    int[] [] mapTileNum  = new int[68][68];

    public TileManager(PanelGame gp){
        this.gp = gp;
        this.tiles = new Tile[32];
        getImgTiles();
    }
    public void getImgTiles(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("images/Environment/Tiles/000.png"));
            
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("images/Environment/Tiles/001.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("images/Environment/Tiles/002.png"));
            
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("images/Environment/Tiles/003.png"));
        
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("images/Environment/Tiles/004.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("images/Environment/Tiles/005.png"));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadMap(String mapPath){

        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                for(int i=0; i<gp.maxWorldRow; i++){
                    String line = br.readLine();
                    String nums[] = line.split(" ");
                    
                    for(int j=0; j<gp.maxWorldCol; j++){
                        int num = Integer.parseInt(nums[j]);
                        mapTileNum[i][j] = num;
                        
                    }
                }
            }

        }catch(IOException | NumberFormatException e){
        }
    }

    public void draw(Graphics2D g2,String map){
        loadMap(map);

        int screenY, screenX , worldX, worldY;
        // i is for y-pos and j is for x-pos

        for(int i=0; i<gp.maxWorldRow; i++){
            for(int j=0; j<gp.maxWorldCol; j++){
                worldX = j * gp.tileSize  * 4;
                worldY = i * gp.tileSize * 4;

                screenX = worldX - gp.player.x + gp.player.cameraX;
                screenY = worldY - gp.player.y + gp.player.cameraY;
                if(worldX > gp.player.x - gp.player.cameraX - (8 * gp.tileSize ) &&
                worldX < gp.player.x + gp.player.cameraX + (8 * gp.tileSize) &&
                worldY > gp.player.y - gp.player.cameraY - ( 8 * gp.tileSize) &&

                worldY < gp.player.y + gp.player.cameraY + ( 8 * gp.tileSize) ){

                g2.drawImage( tiles[ mapTileNum[i][j] ].image, screenX , screenY, gp.tileSize*4, gp.tileSize*4, null );
             }

            }
        }
        /*

        //for(int i=0; i<gp.maxWorldRow; i++){
            for(int j=0; j<gp.maxWorldCol; j++){
                int worldX = j * gp.tileSize;
                int worldY = i * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                   worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                   worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                   worldY < gp.player.worldY + gp.player.screenY + gp.tileSize ){

                    
                }
            }
        }
        
             */    
    }
}
