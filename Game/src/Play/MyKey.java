package Play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKey extends KeyAdapter {
 private SnakePanel snakePanel;

 public MyKey(SnakePanel snakePanel) {
     this.snakePanel = snakePanel;
 }

 public void keyPressed(KeyEvent e) {
     char currentDirection = this.snakePanel.getDirection();
     switch (e.getKeyCode()) {
         case 37:
             if (currentDirection != 'R') {
                 this.snakePanel.setDirection('L');
             }
             break;
         case 38:
             if (currentDirection != 'D') {
                 this.snakePanel.setDirection('U');
             }
             break;
         case 39:
             if (currentDirection != 'L') {
                 this.snakePanel.setDirection('R');
             }
             break;
         case 40:
             if (currentDirection != 'U') {
                 this.snakePanel.setDirection('D');
             }
     }

 }
}

