import java.util.Random;

public class AssetManager {
    PanelGame pg;
    Random choice =  new Random();
    int rand;

    public AssetManager(PanelGame pg){
       this.pg = pg;
    }

    public void placeMonster(){
        for(int i = 0; i < choice.nextInt(9,19); i++){
            pg.Skeletrons[i] = new Skeletron(pg);
            pg.Skeletrons[i].x = choice.nextInt(150, 200);
            pg.Skeletrons[i].y = choice.nextInt(150, 200);
        }
    }
}
