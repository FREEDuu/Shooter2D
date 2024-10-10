import java.util.Random;

public class AssetManager {
    PanelGame pg;
    Random choice =  new Random();
    int rand;

    public AssetManager(PanelGame pg){
       this.pg = pg;
    }

    public void placeMonster(){

        for(int i = 0; i < 1; i++){
            pg.Skeletrons[i] = new Skeletron(pg);
            pg.Skeletrons[i].x = choice.nextInt(150, 400);
            pg.Skeletrons[i].y = choice.nextInt(150, 400);
        }
        for(int i = 0; i < 1; i++){
            pg.Dragonites[i] = new Dragonite(pg);
            pg.Dragonites[i].x = choice.nextInt(100, 400);
            pg.Dragonites[i].y = choice.nextInt(100, 400);
        }
        for(int i = 0; i < 1; i++){
            pg.WhiteMonsters[i] = new WhiteMonster(pg);
            pg.WhiteMonsters[i].x = choice.nextInt(150, 400);
            pg.WhiteMonsters[i].y = choice.nextInt(150, 400);
        }
        if(pg.boss[0] != null){
            pg.boss[0].x = 200;
            pg.boss[0].y = 200;
        }
    }
}
