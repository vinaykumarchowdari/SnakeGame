package Play;

import java.awt.Graphics;


public interface Game {
    void move();

    void gameOver(Graphics var1);

    void checkHit();

    void addFood();

    void checkFood();

    void playGame();

    void draw(Graphics var1);
}
