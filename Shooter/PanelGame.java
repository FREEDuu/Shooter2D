import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class PanelGame extends JPanel implements Runnable{

    //variabili globali del PanelGame
    public int maxWorldCol = 32;
    public int maxWorldRow = 24;
    public int tileSize = 16;
    public Sound SoundM = new Sound();
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public int WorldH = tileSize * maxWorldCol;
    public int WorldY = tileSize * maxWorldRow;
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public int width = gd.getDisplayMode().getWidth();
    public int height = gd.getDisplayMode().getHeight();
    TileManager tileM = new TileManager(this);
    UI uiManager = new UI(this);
    public String playerChoice ; 
    int FPS = 60;
    Thread gameThread;
    public int gameState;
    public int startState = 0;
    public int playState = 1;
    public int pauseState = 2;
    public Player player;
    CollisionDetector cDetector = new CollisionDetector(this);
    ControllerKey controllK = new ControllerKey(this);
    AssetManager assetM = new AssetManager(this);
    public Skeletron[] Skeletrons = new Skeletron[20]; 
    public Dragonite[] Dragonites = new Dragonite[20]; 
    int varx = 100;
    int vary = 100;
    int speed = 10;

    // Costruttore PanelGame, qui con startGame viene avviato il Mainloop del gioco, viene aggiunto il keyListener ecc.
    public PanelGame(){
        this.playerChoice = "toChoise";
        this.gameState = startState;
        this.setPreferredSize(new DimensionUIResource(width, height));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(controllK);
        this.setFocusable(true);
        this.setupGame();
        this.startGame();
    }

    public void startGame(){

        this.gameThread = new Thread(this);
        this.gameThread.start();

    }

    public void setupGame(){
        assetM.placeMonster();
        SoundM.LoopMusicEffect(0);
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
        player = new Player(this, controllK);
    }
    // funzione update chiamata nel gameloop

    public void update(){

        if(playState == gameState){
            player.update();
            if(bullets.size() > 0){
                Utils.checkCollisionBulletsEnemy(Skeletrons, bullets);
                Utils.checkCollisionBulletsEnemy(Dragonites, bullets);
            }
            for(int i = 0; i < Skeletrons.length; i++ ){
                if(Skeletrons[i] != null){
                    Skeletrons[i].update();
                }
            }
            for(int i = 0; i < Dragonites.length; i++ ){
                if(Dragonites[i] != null){
                    Dragonites[i].update();
                }
            }
            Bullet b;
            for(int i = 0; i < bullets.size(); i++){
                b = bullets.get(i);
                if(b != null)
                b.update(b.angle); 
            }
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
            g2.setColor(Color.red);
            tileM.draw(g2, "map.txt");
            player.draw(g2);
            for(int i = 0; i < Skeletrons.length; i++ ){
                if(Skeletrons[i] != null){
                    Skeletrons[i].draw(g2);
                }
            }
            for(int i = 0; i < Dragonites.length; i++ ){
                if(Dragonites[i] != null){
                    Dragonites[i].draw(g2);
                }
            }
            for(int i = 0; i < bullets.size(); i++ ){
                if(bullets.get(i) != null){
                    bullets.get(i).draw(g2); 
                }
            }
            uiManager.draw(g2);
        }
        
    }
}
