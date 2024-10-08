import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class ControllerKey implements KeyListener {

    // variabili che servono per capire che tasto wasd si sta premendo, vengono cambiate a t/f quando viene premuto/rilasciato wasd

    boolean up, down, left, right, shoot, bomb;
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

        if(pg.gameState == pg.loseState){
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
                    pg.RestartGame();
                    pg.uiManager.arrawPos = 0;
                }
                else{
                    System.exit(0);
                }
            }
        }
        if(pg.gameState == pg.nextLevelState){
            if(keycode == KeyEvent.VK_S){
                pg.uiManager.arrawPos++;
                    if(pg.uiManager.arrawPos > 2){
                        pg.uiManager.arrawPos = 0;
                    }
            }
            if(keycode == KeyEvent.VK_W){
                pg.uiManager.arrawPos--;
                if(pg.uiManager.arrawPos < 0){
                    pg.uiManager.arrawPos = 2;
                }
            }
            if (keycode == KeyEvent.VK_ENTER) {

                if(pg.uiManager.arrawPos == 0){
                    pg.player.DamageIncrease();
                }
                if(pg.uiManager.arrawPos == 1){
                    pg.player.HealtIncrease();
                }
                if(pg.uiManager.arrawPos == 2){
                    pg.player.SpeedIncrease();
                }
                pg.setupStartGameLevel();
            }
        }

        if (pg.gameState == pg.startState) {
            if (pg.uiManager.startPage == 0) {
                if(keycode == KeyEvent.VK_D && pg.uiManager.arrawPos == 1){
                    pg.uiManager.selectionDif++;
                    if(pg.uiManager.selectionDif > 3){
                        pg.uiManager.selectionDif = 1;
                    }
                }
                if(keycode == KeyEvent.VK_A && pg.uiManager.arrawPos == 1){
                    pg.uiManager.selectionDif--;
                    if(pg.uiManager.selectionDif < 1){
                        pg.uiManager.selectionDif = 3;
                    }
                }
                if(keycode == KeyEvent.VK_W){
                    pg.uiManager.arrawPos--;
                    if(pg.uiManager.arrawPos < 0){
                        pg.uiManager.arrawPos =2;
                    }
                }
                if(keycode == KeyEvent.VK_S){
                    pg.uiManager.arrawPos++;
                    pg.uiManager.arrawPos = Math.abs(pg.uiManager.arrawPos);
                    pg.uiManager.arrawPos = pg.uiManager.arrawPos % 3 ;
                }
                if (keycode == KeyEvent.VK_ENTER) {
                    if(pg.uiManager.arrawPos == 2){
                        System.exit(0);
                        
                    }
                    else{
                        pg.uiManager.startPage++;
                        pg.uiManager.arrawPos = 0;
                        pg.difficolta = pg.uiManager.selectionDif;
                    }
                }
            }
            else{
                if(keycode == KeyEvent.VK_W){
                    pg.uiManager.arrawPos--;
                    if(pg.uiManager.arrawPos == -1){
                        pg.uiManager.arrawPos = 2;
                    }

                }
                if(keycode == KeyEvent.VK_S){
                    pg.uiManager.arrawPos++;
                    pg.uiManager.arrawPos = Math.abs(pg.uiManager.arrawPos);
                    pg.uiManager.arrawPos = pg.uiManager.arrawPos % 3 ;
                }
                if (keycode == KeyEvent.VK_ENTER) {
                    if(pg.uiManager.arrawPos == 0){
                        pg.playerChoice = "babypunk";
                        pg.setupStartGame();
                        pg.gameState = pg.playState;

                    }
                    else if (pg.uiManager.arrawPos == 1) {
                        pg.playerChoice = "queen";
                        pg.setupStartGame();
                        pg.gameState = pg.playState; 
                    }else{
                        pg.playerChoice = "erbiondo";
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
            if(keycode == KeyEvent.VK_R){
                bomb = true;
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
        if(keycode == KeyEvent.VK_R){
            bomb = false;
        }
    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println("Left button clicked");
        }
    }
}
