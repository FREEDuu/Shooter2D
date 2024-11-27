package manager;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import view.PanelGame;

public class UiDrawing {
    UiManager uiManager;
    int progress = 0; // Counter for color interpolation
    int maxProgress = 150; // Maximum steps for color transition
    Color startColor = Color.white; // Starting color
    Color endColor = Color.black;
    PanelGame pg;
    private Font pixelFont;

    
    public UiDrawing(UiManager uiManager, PanelGame pg){
        this.uiManager = uiManager;
        this.pg = pg;
        loadFont();
    }

    public void drawPlayState(){
    }

    public void loadFont() {
        try {
            // Load the pixelated font from a .ttf file
            File fontFile = new File("Resources/Font/pixel.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, 96F); // Set size to 24
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            pixelFont = new Font("Monospaced", Font.PLAIN, 24); // Fallback to default monospaced font
        }
    }

    public void drawPlayStateUI(Graphics2D g2, PanelGame pg){
        
    }

    public void drawPausedScreen(Graphics2D g2){
        g2.setFont(pixelFont);
        String test = "GIOCO IN PAUSA";
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        test = "(ESC per riprendere)";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*18);
    
        test = "Ricomincia";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
        if (uiManager.arrowPos == 0) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
    
        }
        test = "Volume "+ uiManager.volumeOn;
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
    
    }

    public void draw_startState2(Graphics2D g2, boolean transition){
        
        g2.setFont(pixelFont);
        String test = "Scegli Personaggio";
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
    
        test = "Baby Punk";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
        if (uiManager.arrowPos == 0  && !transition) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
            g2.drawImage(uiManager.imgPG1, pg.width/2 - pg.tileSize*8 , pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
    
        }

        test = "Queen";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
        if (uiManager.arrowPos == 1 && !transition) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
            g2.drawImage(uiManager.imgPG2, pg.width/2 - pg.tileSize*8 , pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
        }

        test = "Er Biondo";
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*20);
        if (uiManager.arrowPos == 2 && !transition) {
            g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*20);
            g2.drawImage(uiManager.imgPG3, pg.width/2 - pg.tileSize*8, pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*20);
        }
    
    }

    public void draw_startState1(Graphics2D g2){

        String test = "SHOOTER 2D";
        g2.setFont(pixelFont);
        g2.setColor(Color.white);
        g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        g2.drawImage(uiManager.imageBoss, pg.width/2 - getXofText(g2, test)/2 , pg.height/2  - pg.tileSize*32, pg.tileSize*16, pg.tileSize*16, null );

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

        g2.setFont(pixelFont);
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

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, pg.width, pg.height);

        g2.setFont(pixelFont);
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
            g2.setFont(pixelFont);
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

    public void drawTransitionLoseState(Graphics2D g2){

        // Calculate progress fraction
        float fraction = progress / (float) maxProgress;
        fraction = Math.min(1.0f, fraction); // Clamp to [0, 1]

        // Interpolate color
        int r = (int) (startColor.getRed() + fraction * (endColor.getRed() - startColor.getRed()));
        int g = (int) (startColor.getGreen() + fraction * (endColor.getGreen() - startColor.getGreen()));
        int b = (int) (startColor.getBlue() + fraction * (endColor.getBlue() - startColor.getBlue()));
        Color currentColor = new Color(r, g, b);
        g2.setColor(currentColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        // Set the new color and draw a rectangle
        g2.fillRect(0,0, pg.width, pg.height);

        // Increment progress for next call
        if (progress < maxProgress) {
            progress++;
        }
        else{
            if (pg.gameState == pg.transitionLoseState) {
                pg.gameState = pg.loseState;
                progress = 0;
            }
            else if(pg.gameState == pg.transitionNextLevel){
                progress = 0;
                pg.nextLevelAfterTransition();
            }
            else {
                pg.gameState = pg.playState;
                progress = 0;             
            }
        }
    
    
    }
    
}
