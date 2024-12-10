package frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entities.Boss;
import entities.Dragonite;
import entities.Entity;
import entities.Player;
import entities.Projectile;
import entities.Skeletron;
import entities.WhiteMonster;
import manager.AssetManager;
import manager.CollisionDetector;
import manager.ControllerKey;
import manager.ControllerUI;
import manager.Sound;
import manager.TileManager;
import manager.UiManager;
import manager.Utils;
import manager.MouseController;

public class PanelGame extends JPanel implements Runnable{

    //variabili globali del PanelGame
    public int maxWorldCol = 100;
    public int maxWorldRow = 100;
    public int tileSize = 16;
    public Sound SoundManager = new Sound();    
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
    public final int transitionLoseState = 7;
    public final int transitionStartGame = 8;
    public final int transitionNextLevel = 9;

    public int gameState = startState1;

    public Player player;

    public CollisionDetector cDetector = new CollisionDetector(this);
    ControllerKey controllK = new ControllerKey(this);
    ControllerUI controllUI = new ControllerUI(this);
    public UiManager uiManager = new UiManager(this, controllK, SoundManager);
    MouseController mouseC = new MouseController(this);
    AssetManager assetM = new AssetManager(this);

    public Entity [][] enemies;
    public Skeletron[] Skeletrons;
    public Dragonite[] Dragonites;
    public WhiteMonster[] WhiteMonsters ;
    public Boss[] boss;
    
    public String[] mapPath = {"map0.txt", "map1.txt", "map2.txt"};

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
        SoundManager.play(10);
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
        SoundManager.stop(10);
        SoundManager.loop(9);
        Lvl =1;
        bombdmg = 90;
        player = new Player(this, controllK, mouseC);
        assetM.replaceAll();
        gameState =playState;
        //SoundM.play(0); da migliorare è bruttissimo xD
    }
    public void setupStartGameLevel(){
        if(Lvl == 1){
            SoundManager.loop(9);
        }
        else if(Lvl == 2){
            SoundManager.loop(12);
            SoundManager.stop(9);
        }
        else{
            SoundManager.stop(9);
            SoundManager.stop(12);
            SoundManager.loop(11);
        }
       player.x = 1600;
       player.y = 1600;
       player.HP.width =player.maxHealth;
        assetM.replaceAll();
        gameState = playState;

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
            this.SoundManager.stop(11);

        }else if(this.Lvl == 2){
            this.SoundManager.stop(12);
        }
        else{
            SoundManager.stop(9);
        }
        gameState = transitionNextLevel;
        SoundManager.play(1);
    }
    public void nextLevelAfterTransition(){
        if(this.Lvl == 3){
            this.gameState = winGameState;
            this.SoundManager.play(6);

        }else if(this.Lvl == 2){
            this.Lvl ++;
            this.gameState = nextLevelState;
        }
        else{
            this.Lvl ++;
            this.gameState = nextLevelState;
        }
    }
}
