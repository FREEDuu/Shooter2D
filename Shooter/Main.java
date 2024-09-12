import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;;

public class Main {
    public static void main(String[] args){

        // classe principale che definisce il Jframe dove verrà messo Il Jpanel gamePanel che visualizza l'intero gioco

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Shooter2D");

        // PanelGame principale

        PanelGame panelgame = new PanelGame();
        frame.add(panelgame);     
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        

    }
}
