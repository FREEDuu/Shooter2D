package manager;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

import View.PanelGame;

public class UiDrawing {
    UiManager uiManager;
    PanelGame pg;
    public UiDrawing(UiManager uiManager, PanelGame pg){
        this.uiManager = uiManager;
        this.pg = pg;
    }

    public void drawPlayState(){
    }

    public void draw_startState2(Graphics2D g2){
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String test = "Scegli Personaggio";
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
    
        test = "Baby Punk";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
        if (uiManager.arrowPos == 0) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
            g2.drawImage(uiManager.imgPG1, pg.width/2 - pg.tileSize*8 , pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
    
        }

        test = "Queen";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
        if (uiManager.arrowPos == 1) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
            g2.drawImage(uiManager.imgPG2, pg.width/2 - pg.tileSize*8 , pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
        }

        test = "Er Biondo";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*20);
        if (uiManager.arrowPos == 2) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*20);
            g2.drawImage(uiManager.imgPG3, pg.width/2 - pg.tileSize*8, pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*20);
        }
    
    }

    public void draw_startState1(Graphics2D g2){

        String test = "SHOOTER 2D";

        g2.drawImage(uiManager.imageBoss, pg.width/32 - getXofText(g2, test)/4 , pg.height/16, pg.tileSize*48, pg.tileSize*48, null );
        g2.drawImage(uiManager.imageDrg, pg.width - getXofText(g2, test)*2 , pg.height/2 - pg.tileSize*20, pg.tileSize*16, pg.tileSize*16, null );
        g2.drawImage(uiManager.imageSklt, pg.width - getXofText(g2, test)*2 , pg.height/2 - pg.tileSize*7 , pg.tileSize*16, pg.tileSize*16, null );
        g2.drawImage(uiManager.imageWhiteMns, pg.width - getXofText(g2, test)*2 , pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null );

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
    
        test = "Avvia Parita";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
        if (uiManager.arrowPos == 0) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
    
        }
        test = "Difficoltà "+Integer.toString(uiManager.selectionDif);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*8);
        if (uiManager.arrowPos == 1) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) ,  pg.height/2 + pg.tileSize*8);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) ,  pg.height/2 + pg.tileSize*8);
    
        }
        test = "Esci dal Gioco";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*16);
        if (uiManager.arrowPos == 2) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*16);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*16);
        }


        test = "Best Record : LvL 10";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);
    }

    public void drawLose(Graphics2D g2){

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, pg.width, pg.height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String test = "Livello Raggiunto : "+ Integer.toString(pg.Lvl);
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
    
        test = "Riavvia Parita";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
        if (uiManager.arrowPos == 0) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
    
        }
        test = "Esci dal Gioco";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
        if (uiManager.arrowPos == 1) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
        }


        test = "Hai Perso ! ! ";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);

    }

    public void drawWin(Graphics2D g2){

        System.out.print('V');
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, pg.width, pg.height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String test = "Hai Vinto il Gioco !!";
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
    
        test = "Riavvia Parita";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
        if (uiManager.arrowPos == 0) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
    
        }


        test = "Esci dal Gioco";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
        if (uiManager.arrowPos == 1) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
        }
        test = "Hai Superato L'ultimo Livello ( 3 ) ";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);

    }

    public void drawNextLevel(Graphics2D g2){
        
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pg.width, pg.height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            g2.setColor(Color.white);
            String test = "Livello : "+ Integer.toString(pg.Lvl-1) + " Superato";
            g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*20);
     
            test = "Cosa Vuoi Aumentare ?";
            g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

            test = "Potenza Sparo ++";
            g2.drawString(test, pg.width/2 - getXofText(g2, test)/2  - pg.tileSize*30,  pg.height/2 - pg.tileSize*2);
            if (uiManager.arrowPos == 0) {
                g2.drawString(">", pg.width/2 - getXofText(g2, test)/2  - pg.tileSize*35,  pg.height/2 - pg.tileSize*2);
                g2.drawString("<", pg.width/2 + getXofText(g2, test)/2  - pg.tileSize*28,  pg.height/2 - pg.tileSize*2);
                g2.drawImage(uiManager.img1,  pg.width/2 - getXofText(g2, test)/2  - pg.tileSize*30 ,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);
        
            }
            test = "Potenza Bomba ++";
            g2.drawString(test, pg.width/2 - getXofText(g2, test)/2, pg.height/2-pg.tileSize*2);
            if (uiManager.arrowPos == 1) {
                g2.drawString(">", pg.width/2 - getXofText(g2, test)/2 - pg.tileSize*4, pg.height/2 -pg.tileSize*2);
                g2.drawString("<", pg.width/2 + getXofText(g2, test)/2 + pg.tileSize*2, pg.height/2 - pg.tileSize*2);
                g2.drawImage(uiManager.img2,  pg.width/2 - getXofText(g2, test)/2 ,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);

            }
            test = "Entrambi +";
            g2.drawString(test, pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*30,  pg.height/2 - pg.tileSize*2);
            if (uiManager.arrowPos == 2) {
                g2.drawString(">", pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*25, pg.height/2 - pg.tileSize*2);
                g2.drawString("<", pg.width/2 + getXofText(g2, test)/2  + pg.tileSize*32, pg.height/2 - pg.tileSize*2);
                g2.drawImage(uiManager.img1,  pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*32 ,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);
                g2.drawImage(uiManager.img2,  pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*30,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);

            }
            test = "SCEGLI IL TUO POTENZIAMENTO";
            g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);

    }

    public int getXofText(Graphics2D g2, String text){
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

    }
}
