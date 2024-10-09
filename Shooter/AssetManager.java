import java.util.Random;

public class AssetManager {
    PanelGame pg;
    Random choice =  new Random();
    int rand;

    public AssetManager(PanelGame pg){
       this.pg = pg;
    }

    public void placeMonster(){

        for(int i = 0; i < choice.nextInt(pg.Lvl*2*pg.difficolta ,pg.Lvl*5*pg.difficolta); i++){
            pg.Skeletrons[i] = new Skeletron(pg);
            pg.Skeletrons[i].x = choice.nextInt(150, 400);
            pg.Skeletrons[i].y = choice.nextInt(150, 400);
        }
        for(int i = 0; i < choice.nextInt(pg.Lvl*2*pg.difficolta,pg.Lvl*5*pg.difficolta); i++){
            pg.Dragonites[i] = new Dragonite(pg);
            pg.Dragonites[i].x = choice.nextInt(100, 400);
            pg.Dragonites[i].y = choice.nextInt(100, 400);
        }
        for(int i = 0; i < choice.nextInt(pg.Lvl*2*pg.difficolta,pg.Lvl*5*pg.difficolta); i++){
            pg.WhiteMonsters[i] = new WhiteMonster(pg);
            pg.WhiteMonsters[i].x = choice.nextInt(150, 400);
            pg.WhiteMonsters[i].y = choice.nextInt(150, 400);
        }

        pg.boss.x = 200;
        pg.boss.y = 200;
    }
}
