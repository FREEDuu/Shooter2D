import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class ControllerKey implements KeyListener {

    // variabili che servono per capire che tasto wasd si sta premendo, vengono cambiate a t/f quando viene premuto/rilasciato wasd

    boolean up, down, left, right, shoot;

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
        if(keycode == KeyEvent.VK_SPACE){
            shoot = true;
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
