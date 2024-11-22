package manager;
import java.awt.event.*;

import view.PanelGame;

public class mouseController extends MouseAdapter{

    PanelGame pg;
    public boolean shoot;

    public mouseController(PanelGame pg){
        this.pg = pg;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shoot = false;
    }        
    
    @Override
    public void mousePressed(MouseEvent e) {
        shoot = true;
    }
}