package manager;
import java.awt.event.*;

import frame.PanelGame;

public class MouseController extends MouseAdapter{

    PanelGame pg;
    public boolean shoot;

    public MouseController(PanelGame pg){
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