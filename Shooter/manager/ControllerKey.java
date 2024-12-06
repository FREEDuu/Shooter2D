package manager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import frame.PanelGame;
public class ControllerKey implements KeyListener {

    // variabili che servono per capire che tasto wasd si sta premendo, vengono cambiate a t/f quando viene premuto/rilasciato wasd

    public boolean up, down, left, right, bomb, enter, escape;
    public boolean upP, downP, leftP, rightP, bombP, enterP, escapeP;
    public boolean upw = false;
    public PanelGame pg;
    public int ReleaseKey = -1;
    public int lastKey = ReleaseKey;

    public ControllerKey(PanelGame pg){
        this.pg = pg;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    

    @Override
    public void keyPressed(KeyEvent e) {

        int keycode = e.getKeyCode();
    
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
        if(keycode == KeyEvent.VK_ESCAPE){
            escape = true;
        }        
        if(keycode == KeyEvent.VK_ENTER){
            enter = true;
        }
        if(keycode == KeyEvent.VK_R){
            bomb = true;
        }
        lastKey = keycode;
    }

    @Override
    public void keyReleased(KeyEvent e){
        int keycode = e.getKeyCode();


        if(keycode == KeyEvent.VK_W){
            up = false;
            upP = false;
            upw = false;
        }
        if(keycode == KeyEvent.VK_S){
            down  = false;
            downP  = false;
        }
        if(keycode == KeyEvent.VK_A){
            left = false;
            leftP = false;
        }
        if(keycode == KeyEvent.VK_D){
            right = false;
            rightP = false;
        }
        if(keycode == KeyEvent.VK_R){
            bomb = false;
            bombP = false;
        }
        if(keycode == KeyEvent.VK_ESCAPE){
            escape = false;
            escapeP = false;
        }        
        if(keycode == KeyEvent.VK_ENTER){
            enter = false;
            enterP = false;
        }
        lastKey = ReleaseKey;
    }
    
}
