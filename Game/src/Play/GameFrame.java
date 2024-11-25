package Play;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
    }

    public static void Frame() {
        GameFrame gm = new GameFrame();
        SnakePanel gp = new SnakePanel();
        gm.setTitle("Snake Game");
        gm.setDefaultCloseOperation(3);
        gm.setResizable(false);
        gm.add(gp);
        gp.setPreferredSize(new Dimension(500, 500));
        gm.pack();
        gm.setVisible(true);
        gm.setLocationRelativeTo((Component)null);
    }
}
