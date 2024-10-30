import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class UI {
    PanelGame pg;
    Graphics2D g2;
    int arrowPos = 0;
    int selectionDif = 1;
    int startPage = 0;
    BufferedImage imageBoss, imageDrg, imageWhiteMns, imageSklt, imgPG1, imgPG2, imgPG3;

    public UI(PanelGame pg){
        this.pg = pg;
        imageBoss = null;
        imageDrg = null;
        imageWhiteMns = null;
        imageSklt = null;
        imgPG1 = null;
        imgPG2 = null;
        imgPG3 = null;
        this.LoadImg();
    }

    public void LoadImg(){
        try{
            imgPG3= ImageIO.read(new File("Shooter/images/16x16/erbiondo.png")).getSubimage(0, 0, 24, 24);
            imgPG2 = ImageIO.read(new File("Shooter/images/16x16/queen.png")).getSubimage(0, 0, 24, 24);
            imgPG1 = ImageIO.read(new File("Shooter/images/16x16/babypunk.png")).getSubimage(0, 0, 24, 24);
            imageSklt = ImageIO.read(new File("Shooter/images/16x16/dragon.png")).getSubimage(0, 0, 24, 24);
            imageWhiteMns = ImageIO.read(new File("Shooter/images/16x16/white.png")).getSubimage(0, 0, 24, 24);
            imageBoss = ImageIO.read(new File("Shooter/images/Boss/orc_attack_down_2.png"));
            imageDrg = ImageIO.read(new File( "Shooter/images/16x16/dragonite.png")).getSubimage(0, 0, 24, 24);

    }   
    catch(Exception e){
        e.printStackTrace();
    }
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

            System.out.println(arrowPos);
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pg.width, pg.height);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String test = "Livello Raggiunto : "+ Integer.toString(pg.Lvl);
            g2.setColor(Color.white);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Riavvia Parita";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrowPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
    
    
            test = "Esci dal Gioco";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
            if (arrowPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
            }
    
    
            test = "Hai Perso ! ! ";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);


        }
        else if(pg.gameState == pg.winGameState){

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pg.width, pg.height);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String test = "Hai Vinto il Gioco !!";
            g2.setColor(Color.white);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Riavvia Parita";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrowPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
    
    
            test = "Esci dal Gioco";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
            if (arrowPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
            }
    
    
            test = "Best Record : LvL 3";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*25);


        }
        else if(pg.gameState == pg.nextLevelState){
            BufferedImage img1, img2;
            img1 = null;
            img2 = null;
            try{
                img1 = ImageIO.read(new File("Shooter/images/projectile/BulletProjectile.png"));
                img2 = ImageIO.read(new File("Shooter/images/Bomb/jump.png"));

            }
            catch(Exception e){
                e.printStackTrace();
            }

            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pg.width, pg.height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            g2.setColor(Color.white);
            String test = "Livello : "+ Integer.toString(pg.Lvl-1) + " Superato";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*20);
     
            test = "Cosa Vuoi Aumentare ?";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

            test = "Potenza Sparo ++";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2  - pg.tileSize*30,  pg.height/2 - pg.tileSize*2);
            if (arrowPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test)/2  - pg.tileSize*35,  pg.height/2 - pg.tileSize*2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test)/2  - pg.tileSize*28,  pg.height/2 - pg.tileSize*2);
                g2.drawImage(img1,  pg.width/2 - getXofText(g2, test)/2  - pg.tileSize*30 ,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);
        
            }
            test = "Potenza Bomba ++";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2, pg.height/2-pg.tileSize*2);
            if (arrowPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test)/2 - pg.tileSize*4, pg.height/2 -pg.tileSize*2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test)/2 + pg.tileSize*2, pg.height/2 - pg.tileSize*2);
                g2.drawImage(img2,  pg.width/2 - getXofText(g2, test)/2 ,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);

            }
            test = "Entrambi +";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*30,  pg.height/2 - pg.tileSize*2);
            if (arrowPos == 2) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*25, pg.height/2 - pg.tileSize*2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test)/2  + pg.tileSize*32, pg.height/2 - pg.tileSize*2);
                g2.drawImage(img1,  pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*32 ,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);
                g2.drawImage(img2,  pg.width/2 - getXofText(g2, test)/2  + pg.tileSize*30,  pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null);

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
            String test = "SHOOTER 2D";

            g2.drawImage(imageBoss, pg.width/32 - getXofText(g2, test)/4 , pg.height/16, pg.tileSize*48, pg.tileSize*48, null );
            g2.drawImage(imageDrg, pg.width - getXofText(g2, test)*2 , pg.height/2 - pg.tileSize*20, pg.tileSize*16, pg.tileSize*16, null );
            g2.drawImage(imageSklt, pg.width - getXofText(g2, test)*2 , pg.height/2 - pg.tileSize*7 , pg.tileSize*16, pg.tileSize*16, null );
            g2.drawImage(imageWhiteMns, pg.width - getXofText(g2, test)*2 , pg.height/2 + pg.tileSize*6, pg.tileSize*16, pg.tileSize*16, null );

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            g2.setColor(Color.white);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 - pg.tileSize*12);
        
            test = "Avvia Parita";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2);
            if (arrowPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
            test = "Difficoltà "+Integer.toString(selectionDif);
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*8);
            if (arrowPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) ,  pg.height/2 + pg.tileSize*8);
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) ,  pg.height/2 + pg.tileSize*8);
        
            }
            test = "Esci dal Gioco";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*16);
            if (arrowPos == 2) {
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
            if (arrowPos == 0) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test) , pg.height/2);
                g2.drawImage(imgPG1, pg.width/2 - pg.tileSize*8 , pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2);
        
            }
    
            test = "Queen";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*10);
            if (arrowPos == 1) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*10);
                g2.drawImage(imgPG2, pg.width/2 - pg.tileSize*8 , pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*10);
            }

            test = "Er Biondo";
            this.g2.drawString(test, pg.width/2 - getXofText(g2, test)/2 , pg.height/2 + pg.tileSize*20);
            if (arrowPos == 2) {
                this.g2.drawString(">", pg.width/2 - getXofText(g2, test), pg.height/2 + pg.tileSize*20);
                g2.drawImage(imgPG3, pg.width/2 - pg.tileSize*8, pg.height/32, pg.tileSize*16, pg.tileSize*16, null );
                this.g2.drawString("<", pg.width/2 + getXofText(g2, test) , pg.height/2 + pg.tileSize*20);
            }
        }

    }


    public int getXofText(Graphics2D g2, String text){
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

    }

}
