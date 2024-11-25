package Play;
import java.util.*;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;

public class SnakePanel extends JPanel implements ActionListener, Game {
    static final int panelheight = 500;
    static final int panelwidth = 500;
    static final int unit_size = 20;
    static final int num_of_units = 625;
    final int[] x = new int[625];
    final int[] y = new int[625];
    int snakelength = 5;
    int foodswallowed;
    private char direction = 'D';
    int foodX;
    int foodY;
    boolean running = false;
    Random random = new Random();
    Timer timer;

    public SnakePanel() {
        this.setSize(500, 500);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKey(this));
        this.playGame();
    }

    public char getDirection() {
        return this.direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void playGame() {
        this.addFood();
        this.running = true;
        this.timer = new Timer(130, this);
        this.timer.start();
    }

    public void addFood() {
        this.foodX = this.random.nextInt(25) * 20;
        this.foodY = this.random.nextInt(25) * 20;
    }

    public void actionPerformed(ActionEvent e) {
        if (this.running) {
            this.move();
            this.checkFood();
            this.checkHit();
        }

        this.repaint();
    }

    public void move() {
        for(int i = this.snakelength; i > 0; --i) {
            this.x[i] = this.x[i - 1];
            this.y[i] = this.y[i - 1];
        }

        if (this.direction == 'L') {
            this.x[0] -= 20;
        } else if (this.direction == 'R') {
            this.x[0] += 20;
        } else if (this.direction == 'U') {
            this.y[0] -= 20;
        } else {
            this.y[0] += 20;
        }

    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", 0, 50));
        FontMetrics metrics = this.getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (500 - metrics.stringWidth("Game Over")) / 2, 250);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", 0, 25));
        metrics = this.getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + this.foodswallowed, (500 - metrics.stringWidth("Score: " + this.foodswallowed)) / 2, graphics.getFont().getSize());
    }

    public void checkHit() {
        for(int i = this.snakelength; i > 0; --i) {
            if (this.x[0] == this.x[i] && this.y[0] == this.y[i]) {
                this.running = false;
            }
        }

        if (this.x[0] < 0 || this.x[0] > 500 || this.y[0] < 0 || this.y[0] > 500) {
            this.running = false;
        }

        if (!this.running) {
            this.timer.stop();
        }

    }

    public void draw(Graphics graphics) {
        if (this.running) {
            graphics.setColor(new Color(214, 0, 0));
            graphics.fillOval(this.foodX, this.foodY, 20, 20);
            graphics.setColor(Color.white);
            graphics.fillRect(this.x[0], this.y[0], 20, 20);

            for(int i = 1; i < this.snakelength; ++i) {
                graphics.setColor(new Color(212, 100, 215));
                graphics.fillRect(this.x[i], this.y[i], 20, 20);
            }

            graphics.setColor(Color.red);
            graphics.setFont(new Font("Sans serif", 0, 25));
            FontMetrics metrics = this.getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + this.foodswallowed, (500 - metrics.stringWidth("Score: " + this.foodswallowed)) / 2, graphics.getFont().getSize());
        } else {
            this.gameOver(graphics);
        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.draw(graphics);
    }

    public void checkFood() {
        if (this.x[0] == this.foodX && this.y[0] == this.foodY) {
            ++this.snakelength;
            ++this.foodswallowed;
            this.addFood();
        }

    }
}