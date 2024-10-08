import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    PanelGame pg;
    Graphics2D g2;
    int arrawPos = 0;
    int selectionDif = 1;
    int startPage = 0;

    public UI(PanelGame pg){
        this.pg = pg;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        g2.setColor(Color.white);

        if(pg.gameState == pg.playState){
            // disegna vita e mana
            String test = "Livello " + Integer.toString(pg.Lvl);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.tileSize*4);

        }
        else if(pg.gameState == pg.loseState){

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pg.width, pg.height);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String test = "Livello Raggiunto : "+ Integer.toString(pg.Lvl);
            g2.setColor(Color.white);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Riavvia Parita";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrawPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
    
    
    
            test = "Esci dal Gioco";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
            if (arrawPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
            }
    
    
            test = "Best Record : LvL 10";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);


        }
        else if(pg.gameState == pg.nextLevelState){

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pg.width, pg.height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            g2.setColor(Color.white);
            String test = "Livello : "+ Integer.toString(pg.Lvl-1) + " Superato";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*20);
     
            test = "Cosa Vuoi Aumentare ?";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Attacco";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrawPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
            test = "Salute";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*8);
            if (arrawPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*8);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*8);
            }
            test = "Velocità";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*16);
            if (arrawPos == 2) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*16);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*16);
            }
    
            test = "SCEGLI IL TUO POTENZIAMENTO";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);


        }
        else if(pg.gameState == pg.pauseState){
            // metti in pausa
            drawPausedScreen();
        }
        else if(pg.gameState == pg.startState){
            drawStartScreen();
        }
    }   
    public void drawPausedScreen(){
        String test = "PAUSA";

        this.g2.drawString(test, pg.width/2, pg.height/2);
    }
    public void drawStartScreen(){

        if (startPage == 0) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String test = "SHOOTER 2D";
            g2.setColor(Color.white);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Avvia Parita";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrawPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
            test = "Difficoltà "+Integer.toString(selectionDif);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*8);
            if (arrawPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) ,  pg.height/2 + pg.tileSize*8);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) ,  pg.height/2 + pg.tileSize*8);
        
            }
            test = "Esci dal Gioco";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*16);
            if (arrawPos == 2) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*16);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*16);
            }
    
    
            test = "Best Record : LvL 10";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);
        }else{

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String test = "Scegli Personaggio";
            g2.setColor(Color.white);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Baby Punk";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrawPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
    
    
    
            test = "Queen";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
            if (arrawPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
            }

            test = "Er Biondo";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*20);
            if (arrawPos == 2) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*20);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*20);
            }
        }


    }


    public int getXofText(Graphics2D g2, String text){
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

    }

}
