package manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import view.PanelGame;

import java.io.File;

public class UiManager {
    public final int startState1 = 0;
    public final int startState2 = 1;
    public final int playState = 2;
    public final int pauseState = 3;
    public final int loseState = 4;
    public final int nextLevelState = 5;
    public final int winGameState = 6;
    public final int transitionLoseState = 7;
    public final int transitionStartGame = 8;
    PanelGame pg;
    ControllerKey ck;
    Graphics2D g2;
    BufferedImage img1, img2;
    int arrowPos = 0;
    int selectionDif = 1;
    int startPage = 0;
    BufferedImage imageBoss, imageDrg, imageWhiteMns, imageSklt, imgPG1, imgPG2, imgPG3;
    UiDrawing uiDrawing;

    public UiManager(PanelGame pg, ControllerKey ck){
        this.pg = pg;
        this.ck = ck;
        imageBoss = null;
        imageDrg = null;
        imageWhiteMns = null;
        imageSklt = null;
        imgPG1 = null;
        imgPG2 = null;
        imgPG3 = null;
        this.uiDrawing = new UiDrawing(this, pg);
        this.LoadImg();
    }

    public void LoadImg(){

        img1 = null;
        img2 = null;
        try{
            img1 = ImageIO.read(new File("Resources/projectile/BulletProjectile.png"));
            img2 = ImageIO.read(new File("Resources/Bomb/jump.png"));

        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            imgPG3= ImageIO.read(new File("Resources/16x16/erbiondo.png")).getSubimage(0, 0, 24, 24);
            imgPG2 = ImageIO.read(new File("Resources/16x16/queen.png")).getSubimage(0, 0, 24, 24);
            imgPG1 = ImageIO.read(new File("Resources/16x16/babypunk.png")).getSubimage(0, 0, 24, 24);
            imageSklt = ImageIO.read(new File("Resources/16x16/dragon.png")).getSubimage(0, 0, 24, 24);
            imageWhiteMns = ImageIO.read(new File("Resources/16x16/white.png")).getSubimage(0, 0, 24, 24);
            imageBoss = ImageIO.read(new File("Resources/Boss/orc_attack_down_2.png"));
            imageDrg = ImageIO.read(new File( "Resources/16x16/dragonite.png")).getSubimage(0, 0, 24, 24);

        }   
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        switch (pg.gameState) {
            case startState1:
                uiDrawing.draw_startState1(g2);
                break;
            case startState2:
                uiDrawing.draw_startState2(g2);
                break;
            case playState:
                break;
            case pauseState:
                drawPausedScreen();
                break;
            case nextLevelState: 
                uiDrawing.drawNextLevel(g2);
                break;
            case loseState:
                uiDrawing.drawLose(g2);
                break;  
            case transitionLoseState:
                uiDrawing.drawTransitionLoseState(g2);
                break;
            case winGameState:
                uiDrawing.drawWin(g2);
                break;               
            case transitionStartGame:
                uiDrawing.draw_startState2(g2);
                uiDrawing.drawTransitionLoseState(g2);
                break;        
            default:
                System.out.println("FAILED");    
                break;
        }
    }
        
    public void upKeyPressed(){
        arrowPos--;
        if (arrowPos < 0) {
            arrowPos = 2;
        }
    }    
    public void escapeKeyPressed(){
        if(pg.gameState == playState){
            pg.gameState = pauseState;
        }
        else{
            pg.gameState = playState;
        }
    }
    public void enterKeyPressed(){
        switch (pg.gameState) {
            case startState1:
                if (arrowPos == 2) {
                    System.exit(0);
                }
                gameStart2();
                break;
            case startState2:
                gameStart();
                break;
            case nextLevelState:
                nextUiLevel();
                break;
            default:
                endDisplay();
                break;
        }
    }
    public void downKeyPressed(){
        arrowPos = Math.abs((arrowPos + 1)) % 3;
    }
    public void leftKeyPressed(){
        if (pg.gameState == startState1 && arrowPos == 1) {
            selectionDif--;
            if (selectionDif < 1) {
                selectionDif = 3;
            }
        }
        else{
            arrowPos = Math.abs((arrowPos + 1)) % 3;
        }
    }
    public void rightKeyPressed(){
        if(arrowPos == 1 && pg.gameState == startState1){
            selectionDif++;
            if (selectionDif > 3) {
                selectionDif = 1;
            }
        }
        else{
            arrowPos--;
            if (arrowPos < 0) {
                arrowPos = 2;
            }
        }
    }
    public void drawPausedScreen(){
        String test = "PAUSA";

        this.g2.drawString(test, pg.width/2, pg.height/2);
    }
    public void gameStart(){
        if(pg.uiManager.arrowPos == 0){
            pg.playerChoice = "babypunk";
            pg.setupStartGame();
            pg.gameState = pg.transitionStartGame;
        }
        else if (pg.uiManager.arrowPos == 1) {
            pg.playerChoice = "queen";
            pg.setupStartGame();
            pg.gameState = pg.transitionStartGame; 
        }else{
            pg.playerChoice = "erbiondo";
            pg.setupStartGame();
            pg.gameState = pg.transitionStartGame; 
        }
        pg.SoundIntroMusic.stop();
        pg.uiManager.arrowPos = 0;
        // draw game
    }

    public void gameStart2(){
        if(arrowPos == 2){
            System.exit(0);
            
        }
        else{
            pg.gameState = pg.startState2;
            pg.uiManager.arrowPos = 0;
            pg.difficolta = pg.uiManager.selectionDif;
        }

        // draw game
    }
    public void nextUiLevel(){
        if(pg.uiManager.arrowPos == 0){
            pg.player.DamageIncrease();
        }
        if(pg.uiManager.arrowPos == 1){
            pg.player.BombIncrease();
        }
        if(pg.uiManager.arrowPos == 2){
            pg.player.BothIncrease();
        }
        pg.uiManager.arrowPos = 0;
        pg.SoundIntroMusic.stop();
        pg.SoundWhilePlay.play();
        pg.SoundPowerup.play();
        pg.setupStartGameLevel();

        //draw game
    }
    public void endDisplay(){
        if(pg.uiManager.arrowPos == 0){

            pg.setupStartGame();

        }

        if(pg.uiManager.arrowPos == 1){
            System.exit(0);
        }

    }

}
