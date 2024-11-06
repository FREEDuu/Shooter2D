package View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Controller.AssetManager;
import Controller.CollisionDetector;
import Controller.ControllerKey;
import Controller.Sound;
import Controller.TileManager;
import Controller.UI;
import Controller.Utils;
import Controller.mouseController;
import Model.Boss;
import Model.Dragonite;
import Model.Entity;
import Model.Player;
import Model.Projectile;
import Model.Skeletron;
import Model.WhiteMonster;

public class PanelGame extends JPanel implements Runnable{

    //variabili globali del PanelGame
    public int maxWorldCol = 100;
    public int maxWorldRow = 100;
    public int tileSize = 16;
    public Sound SoundM = new Sound();
    public Sound SoundIntroMusic = new Sound();
    public Sound SoundWhilePlay = new Sound();
    public Sound SoundBoss = new Sound();

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
    public UI uiManager = new UI(this);

    public String playerChoice ; 
    int FPS = 60;
    public boolean ret = false;
    Thread gameThread;
    public int Lvl = 1;
    public int difficolta;
    public int startState = 0;
    public int gameState = startState;
    public int playState = 1;
    public int pauseState = 2;
    public int loseState = 3;
    public int nextLevelState = 4;
    public int winGameState = 5;
    public Player player;

    public CollisionDetector cDetector = new CollisionDetector(this);
    ControllerKey controllK = new ControllerKey(this);
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
        this.gameState = startState;
        this.tileM.loadMap(mapPath[0]);
        this.setPreferredSize(new DimensionUIResource(width, height));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(controllK);
        this.addMouseListener(mouseC);
        this.setFocusable(true);
        this.startGame();
    }

    public void startGame(){

        this.gameThread = new Thread(this);
        this.gameThread.start();
        SoundIntroMusic.LoopMusicEffect(10);
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
        SoundWhilePlay.LoopMusicEffect(9);
        Lvl =1;
        player = new Player(this, controllK, mouseC);
        assetM.replaceAll();
        gameState = playState;
        //SoundM.LoopMusicEffect(0); da migliorare è bruttissimo xD
    }
    public void setupStartGameLevel(){
        if(Lvl < 3){
            SoundWhilePlay.play();
        }
        else{
            SoundWhilePlay.stop();
            SoundBoss.LoopMusicEffect(11);
        }
        player.x = 1300;
        player.y = 1300;
        assetM.replaceAll();
        gameState = playState;

        //SoundM.LoopMusicEffect(0); da migliorare è bruttissimo xD
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
        if(gameState == startState){
            uiManager.draw(g2);
        }
        else{
            assetM.allPaint(g2);
        }
    }

    public void nextLevel(){
        SoundWhilePlay.stop();
        if(this.Lvl == 3){
            this.SoundBoss.stop();
            this.SoundBoss.PlaySoundEffect(6);
            this.gameState = winGameState;
        }
        else{
            SoundM.PlaySoundEffect(1);
            this.Lvl ++;
            this.gameState = nextLevelState;
        }
    }
}
