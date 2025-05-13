package shootinggame;

import java.awt.*;
import java.util.Random;

public class Enemy {
    public int x, y;
    private int width = 40;
    private int height = 20;
    private int speed;
    private Random rand = new Random();

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = rand.nextInt(3) + 2; // 2〜4のランダムスピード
    }

    public void move() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
