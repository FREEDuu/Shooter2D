import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class ControllerKey implements KeyListener {

    // variabili che servono per capire che tasto wasd si sta premendo, vengono cambiate a t/f quando viene premuto/rilasciato wasd

    boolean up, down, left, right, shoot;
    PanelGame pg;

    public ControllerKey(PanelGame pg){
        this.pg = pg;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (pg.gameState == pg.startState) {
            if (pg.uiManager.startPage == 0) {
                if(keycode == KeyEvent.VK_W){
                    pg.uiManager.arrawPos++;
                    pg.uiManager.arrawPos = pg.uiManager.arrawPos % 2 ;
    
                }
                if(keycode == KeyEvent.VK_S){
                    pg.uiManager.arrawPos--;
                    pg.uiManager.arrawPos = Math.abs(pg.uiManager.arrawPos);
                    pg.uiManager.arrawPos = pg.uiManager.arrawPos % 2 ;
                }
                if (keycode == KeyEvent.VK_ENTER) {
                    if(pg.uiManager.arrawPos == 0){
                        pg.uiManager.startPage++;
                    }
                    else{
                        System.exit(0);
                    }
                }
            }
            else{
                if(keycode == KeyEvent.VK_W){
                    pg.uiManager.arrawPos++;
                    pg.uiManager.arrawPos = pg.uiManager.arrawPos % 2 ;
    
                }
                if(keycode == KeyEvent.VK_S){
                    pg.uiManager.arrawPos--;
                    pg.uiManager.arrawPos = Math.abs(pg.uiManager.arrawPos);
                    pg.uiManager.arrawPos = pg.uiManager.arrawPos % 2 ;
                }
                if (keycode == KeyEvent.VK_ENTER) {
                    if(pg.uiManager.arrawPos == 0){
                        pg.playerChoice = "Pistolero";
                        pg.setupStartGame();
                        pg.gameState = pg.playState;

                    }
                    else{
                        pg.playerChoice = "Mago";
                        pg.setupStartGame();
                        pg.gameState = pg.playState; 
                    }
                }
            }


        }
        else{
            if(keycode == KeyEvent.VK_W){
                up = true;
            }
            if(keycode == KeyEvent.VK_S){
                down  = true;
            }
            if(keycode == KeyEvent.VK_A){
                left = true;
            }
            if(keycode == KeyEvent.VK_D){
                right = true;
            }
            if(keycode == KeyEvent.VK_SPACE){
                shoot = true;
            }
            if(keycode == KeyEvent.VK_ESCAPE){
                if(pg.gameState == 1){
                    pg.gameState = 2;
                }
                else if(pg.gameState == 2){
                    pg.gameState = 1;
                }
            }
        }



    }
    @Override
    public void keyReleased(KeyEvent e){
        int keycode = e.getKeyCode();


        if(keycode == KeyEvent.VK_W){
            up = false;
        }
        if(keycode == KeyEvent.VK_S){
            down  = false;
        }
        if(keycode == KeyEvent.VK_A){
            left = false;
        }
        if(keycode == KeyEvent.VK_D){
            right = false;
        }
        if(keycode == KeyEvent.VK_SPACE){
            shoot = false;
        }
    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println("Left button clicked");
        }
    }
}
