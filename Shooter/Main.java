import javax.swing.JFrame;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args){

        // classe principale che definisce il Jframe dove verrà messo Il Jpanel gamePanel che visualizza l'intero gioco

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("Shooter2D");

        // PanelGame principale

        PanelGame panelgame = new PanelGame();
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Image cursore = null;
        try{
            cursore = ImageIO.read(new File("Shooter/images/Weapons/crosshair.png")).getSubimage(38, 10, 18,18).getScaledInstance(100, 100, 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Point point = new Point(0, 0);
        Cursor cursor = toolKit.createCustomCursor(cursore, point, "Cursor");
        panelgame.setCursor(cursor);

        frame.add(panelgame);     
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        

    }
}
