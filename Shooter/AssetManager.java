import java.util.Random;

public class AssetManager {
    PanelGame pg;
    Random choise =  new Random();
    int rand;

    public AssetManager(PanelGame pg){
       this.pg = pg;
    }

    public void placeMonster(){
        for(int i = 0; i < choise.nextInt(19); i++){
            pg.Skeletrons[i] = new Skeletron(pg);
            pg.Skeletrons[i].x = choise.nextInt(150, 200);
            pg.Skeletrons[i].y = choise.nextInt(150, 200);
        }
    }
}
