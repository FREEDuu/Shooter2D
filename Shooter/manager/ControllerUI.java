package manager;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import view.PanelGame;

import java.awt.event.KeyEvent;


public class ControllerUI implements KeyListener {
    private final Set<Integer> pressedKeys = new HashSet<>();
    public PanelGame pg;

    public ControllerUI(PanelGame pg){
        this.pg = pg;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Add the key to the pressedKeys set only if it wasn't already in the set
        if (!pressedKeys.contains(e.getKeyCode())) {
            int keycode = e.getKeyCode();
    
            if(keycode == KeyEvent.VK_W){
                pg.uiManager.upKeyPressed();
            }
            if(keycode == KeyEvent.VK_S){
                pg.uiManager.downKeyPressed();
            }
            if(keycode == KeyEvent.VK_A){
                pg.uiManager.leftKeyPressed();
            }
            if(keycode == KeyEvent.VK_D){
                pg.uiManager.rightKeyPressed();
            }
            if(keycode == KeyEvent.VK_ESCAPE){
                pg.uiManager.escapeKeyPressed();
            }        
            if(keycode == KeyEvent.VK_ENTER){
                pg.uiManager.enterKeyPressed();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Remove the key from the set when released
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This can remain empty for this purpose
    }


}
