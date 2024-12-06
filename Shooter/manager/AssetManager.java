package manager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import entity.Boss;
import entity.Dragonite;
import entity.Entity;
import entity.Projectile;
import entity.Skeletron;
import entity.WhiteMonster;
import frame.PanelGame;

public class AssetManager {
    PanelGame pg;
    Random choice =  new Random();
    int rand;

    public AssetManager(PanelGame pg){
       this.pg = pg;
    }

    public void replaceAll(){

        pg.Potions = new ArrayList<Entity>();
        pg.BombPlayerList = new ArrayList<Projectile>(); 
        pg.bullets = new ArrayList<Projectile>();
        pg.WhiteMonsterbullets = new ArrayList<Projectile>();
        pg.Skeletrons = new Skeletron[100]; 
        pg.WhiteMonsters = new WhiteMonster[100]; 
        pg.Dragonites = new Dragonite[100]; 
        pg.boss = new Boss[]{new Boss(pg)};
        pg.enemies = new Entity[][]{ pg.Skeletrons, pg.Dragonites, pg.WhiteMonsters, pg.boss};

    for(int i = 0; i<pg.Lvl * pg.difficolta; i++){
            pg.Skeletrons[i] = new Skeletron(pg);
            pg.Skeletrons[i].x = choice.nextInt(3500, 3700);
            pg.Skeletrons[i].y = choice.nextInt(3500, 3700);
        }
        for(int i = 0; i < pg.Lvl * pg.difficolta;i++){
            pg.Dragonites[i] = new Dragonite(pg);
            pg.Dragonites[i].x = choice.nextInt(3500, 3700);
            pg.Dragonites[i].y = choice.nextInt(3500, 3700);
        }
        for(int i = 0; i < pg.Lvl * pg.difficolta; i++){
            pg.WhiteMonsters[i] = new WhiteMonster(pg);
            pg.WhiteMonsters[i].x = choice.nextInt(3500, 3700);
            pg.WhiteMonsters[i].y = choice.nextInt(3500, 3700);
        }
    
        if(pg.Lvl == 3){
            pg.boss[0] = new Boss(pg);
            pg.boss[0].x = choice.nextInt(3500, 3700);
            pg.boss[0].y = choice.nextInt(3500, 3700);
        }
        else{
            pg.boss[0] = null;
        }
    }

    public void allUpdate(){
        pg.player.update();
            if(pg.boss[0] != null){
                pg.boss[0].update();
                pg.ret = false;
            }
            for(int i = 0; i < pg.Skeletrons.length; i++ ){
                if(pg.Skeletrons[i] != null){
                    pg.Skeletrons[i].update();
                    pg.ret = false;
                }
            }
            for(int i = 0; i < pg.Dragonites.length; i++ ){
                if(pg.Dragonites[i] != null){
                    pg.Dragonites[i].update();
                    pg.ret = false;
                }
            }
            for(int i = 0; i < pg.WhiteMonsters.length; i++ ){
                if(pg.WhiteMonsters[i] != null){
                    pg.WhiteMonsters[i].update();
                    pg.ret = false;
                }
            }
            Projectile b;
            for(int i = 0; i < pg.bullets.size(); i++){
                b = pg.bullets.get(i);
                if(b != null)
                b.update(); 
            }
            for(int i = 0; i < pg.WhiteMonsterbullets.size(); i++){
                b = pg.WhiteMonsterbullets.get(i);
                if(b != null)
                b.update(); 
            }
            for(int i = 0; i < pg.BombPlayerList.size(); i++){
                b = pg.BombPlayerList.get(i);
                if(b != null && !b.collision)
                b.update(); 
            }
            if(pg.ret){
                pg.nextLevel();
            }
    }

    public void allPaint(Graphics2D g2){
            if(pg.gameState == pg.playState || pg.gameState >= pg.transitionLoseState){
                g2.setColor(Color.red);
            pg.tileM.draw(g2, pg.mapPath[pg.Lvl-1]);
            pg.player.draw(g2);
            for(int i = 0; i < pg.Skeletrons.length; i++ ){
                if(pg.Skeletrons[i] != null){
                    pg.Skeletrons[i].draw(g2);
                }
            }
            for(int i = 0; i < pg.Dragonites.length; i++ ){
                if(pg.Dragonites[i] != null){
                    pg.Dragonites[i].draw(g2);
                }
            }
            for(int i = 0; i < pg.WhiteMonsters.length; i++ ){
                if(pg.WhiteMonsters[i] != null){
                    pg.WhiteMonsters[i].draw(g2);
                }
            }
            for(int i = 0; i < pg.bullets.size(); i++ ){
                if(pg.bullets.get(i) != null){
                    pg.bullets.get(i).draw(g2); 
                }
            }
            for(int i = 0; i < pg.WhiteMonsterbullets.size(); i++ ){
                if(pg.WhiteMonsterbullets.get(i) != null){
                    pg.WhiteMonsterbullets.get(i).draw(g2); 
                }
            }
            for(int i = 0; i < pg.BombPlayerList.size(); i++ ){
                if(pg.BombPlayerList.get(i) != null && pg.BombPlayerList.get(i).collision == false){
                    pg.BombPlayerList.get(i).draw(g2); 
                }
            }
            for(int i = 0; i < pg.Potions.size(); i++ ){

                if(pg.Potions.get(i) != null){
                    pg.Potions.get(i).draw(g2);
                }
            }
            if(pg.boss[0] != null){
                pg.boss[0].draw(g2);
            }
            }
            
            pg.uiManager.draw(g2);
    }
}
