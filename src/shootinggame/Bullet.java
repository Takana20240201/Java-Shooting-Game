package shootinggame;

import java.awt.*;

public class Bullet {
    private int x, y;
    private int width = 5;
    private int height = 10;
    private int speed = 10;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

    public boolean isOffScreen() {
        return y + height < 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
