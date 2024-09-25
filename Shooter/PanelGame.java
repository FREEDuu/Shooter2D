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
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public int WorldH = tileSize * maxWorldCol;
    public int WorldY = tileSize * maxWorldRow;
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public int width = gd.getDisplayMode().getWidth();
    public int height = gd.getDisplayMode().getHeight();
    TileManager tileM = new TileManager(this);
    int FPS = 45;
    Thread gameThread;
    CollisionDetector cDetector = new CollisionDetector(this);
    ControllerKey controllK = new ControllerKey();
    AssetManager assetM = new AssetManager(this);
    public Player player = new Player(this, controllK);
    public Skeletron[] Skeletrons = new Skeletron[20]; 
    int varx = 100;
    int vary = 100;
    int speed = 10;

    // Costruttore PanelGame, qui con startGame viene avviato il Mainloop del gioco, viene aggiunto il keyListener ecc.
    public PanelGame(){

        this.setPreferredSize(new DimensionUIResource(width, height));
        this.setDoubleBuffered(true);
        this.setBackground(Color.white);
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

    // funzione update chiamata nel gameloop

    public void update(){
        player.update();
        for(int i = 0; i < Skeletrons.length; i++ ){
            if(Skeletrons[i] != null){
                Skeletrons[i].update();
            }
        }
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).update(bullets.get(i).angle); 
        }
    }

    // funzione generica predefinita di swing che eredita da paintcomponent base

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        tileM.draw(g2, "map.txt");
        player.draw(g2);
        for(int i = 0; i < Skeletrons.length; i++ ){
            if(Skeletrons[i] != null){
                Skeletrons[i].draw(g2);
            }
        }
        for(int i = 0; i < bullets.size(); i++ ){
            bullets.get(i).draw(g2); 
        }
    }
}
