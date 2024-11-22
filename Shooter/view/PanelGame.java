package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import manager.AssetManager;
import manager.CollisionDetector;
import manager.ControllerKey;
import manager.ControllerUI;
import manager.Sound;
import manager.TileManager;
import manager.UiManager;
import manager.Utils;
import manager.mouseController;
import model.Boss;
import model.Dragonite;
import model.Entity;
import model.Player;
import model.Projectile;
import model.Skeletron;
import model.WhiteMonster;

public class PanelGame extends JPanel implements Runnable{

    //variabili globali del PanelGame
    public int maxWorldCol = 100;
    public int maxWorldRow = 100;
    public int tileSize = 16;
    public Sound SoundShootPlayer = new Sound(2);
    public Sound SoundIntroMusic = new Sound(10);
    public Sound SoundWhilePlay = new Sound(9);
    public Sound SoundBoss = new Sound(11);
    public Sound SoundLevelup = new Sound(1);
    public Sound SoundLose = new Sound(8);
    public Sound SoundPowerup = new Sound(7);
    public Sound SoundPotion = new Sound(3);
    public Sound SoundHitMonster = new Sound(4);
    public Sound SoundMonsterDeath = new Sound(5);    
    public Sound SoundLvl2 = new Sound(12);
    public Sound SoundWinGame = new Sound(6);
    public Sound SoundBomb = new Sound(0);
    
    public List<Projectile> bullets = new ArrayList<Projectile>();
    public List<Projectile> WhiteMonsterbullets = new ArrayList<Projectile>();
    public List<Projectile> BombPlayerList = new ArrayList<Projectile>();
    public List<Entity> Potions = new ArrayList<Entity>();
    public int WorldH = tileSize * maxWorldCol;
    public int WorldY = tileSize * maxWorldRow;

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public int width = gd.getDisplayMode().getWidth();
    public int height = gd.getDisplayMode().getHeight();
    public TileManager tileM = new TileManager(this);

    public String playerChoice ; 
    int FPS = 60;
    public boolean ret = false;
    public int bombdmg = 90;
    Thread gameThread;
    public int Lvl = 1;
    public int difficolta;
    public final int startState1 = 0;
    public final int startState2 = 1;
    public final int playState = 2;
    public final int pauseState = 3;
    public final int loseState = 4;
    public final int nextLevelState = 5;
    public final int winGameState = 6;
    public int gameState = startState1;

    public Player player;

    public CollisionDetector cDetector = new CollisionDetector(this);
    ControllerKey controllK = new ControllerKey(this);
    ControllerUI controllUI = new ControllerUI(this);
    public UiManager uiManager = new UiManager(this, controllK);
    mouseController mouseC = new mouseController(this);
    AssetManager assetM = new AssetManager(this);

    public Entity [][] enemies;
    public Skeletron[] Skeletrons;
    public Dragonite[] Dragonites;
    public WhiteMonster[] WhiteMonsters ;
    public Boss[] boss;
    
    public String[] mapPath = {"map0.txt", "map1.txt", "map2.txt"};
    int varx = 100;
    int vary = 100;
    int speed = 10;

    // Costruttore PanelGame, qui con startGame viene avviato il Mainloop del gioco, viene aggiunto il keyListener ecc.
    public PanelGame(){
        this.playerChoice = "toChoice";
        this.gameState = startState1;
        this.tileM.loadMap(mapPath[0]);
        this.setPreferredSize(new DimensionUIResource(width, height));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(controllK);
        this.addKeyListener(controllUI);
        this.addMouseListener(mouseC);
        this.setFocusable(true);
        this.startGame();
    }

    public void startGame(){

        this.gameThread = new Thread(this);
        this.gameThread.start();
        SoundIntroMusic.play();
    }
    
    //funzione builtin di swing chiamata in gamethread.start

    @Override
    public void run(){

        // implementazione manuale di un timer per un framerate costante

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


       // game loop principale timer ---> update ---> repaint ---> timer ---> ecc.

        while(this.gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta --;
                
            }
        }
    }
    public void setupStartGame(){
        SoundIntroMusic.stop();
        SoundWhilePlay.loop();
        Lvl =1;
        bombdmg = 90;
        player = new Player(this, controllK, mouseC);
        assetM.replaceAll();
        gameState =playState;
        //SoundM.play(0); da migliorare è bruttissimo xD
    }
    public void setupStartGameLevel(){
        if(Lvl == 1){
            SoundWhilePlay.loop();
        }
        else if(Lvl == 2){
            SoundLvl2.loop();
            SoundWhilePlay.stop();
        }
        else{
            SoundWhilePlay.stop();
            SoundLvl2.stop();
            SoundBoss.loop();
        }
       player.x = 1600;
       player.y = 1600;
       player.HP.width =player.maxHealth;
        assetM.replaceAll();
        gameState =playState;

        //SoundM.play(0); da migliorare è bruttissimo xD
    }
    // funzione update chiamata nel gameloop

    public void update(){

        if(playState == gameState){
            ret = true;
            if(enemies != null){
                Utils.onLifeEnemy(enemies);
            }
            if(Potions.size() > 0){
                Utils.checkPotions(player, Potions);
            }
            if(bullets.size() > 0){
                Utils.checkCollisionBulletsEnemy(Skeletrons, bullets);
                Utils.checkCollisionBulletsEnemy(Dragonites, bullets);
                Utils.checkCollisionBulletsEnemy(WhiteMonsters, bullets);
                Utils.checkCollisionBulletsEnemy(boss, bullets);
            }
            if(WhiteMonsterbullets.size() > 0){
                Utils.checkWhiteBulletPlayer(player, WhiteMonsterbullets);
            }
            assetM.allUpdate();
        }

    }

    // funzione generica predefinita di swing che eredita da paintcomponent base

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        assetM.allPaint(g2);
        uiManager.draw(g2);
    }

    public void nextLevel(){
        if(this.Lvl == 3){
            this.SoundBoss.stop();
            this.SoundWinGame.play();
            this.gameState = winGameState;

        }else if(this.Lvl == 2){
            SoundLevelup.play();
            this.Lvl ++;
            this.SoundLvl2.stop();
            this.gameState = nextLevelState;
        }
        else{
            SoundWhilePlay.stop();
            SoundLevelup.play();
            this.Lvl ++;
            this.gameState = nextLevelState;
        }
    }
}
