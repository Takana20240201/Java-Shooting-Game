package shootinggame;

import java.awt.*;

public class Player {
    private int x, y;
    private int width = 40;
    private int height = 20;
    private int speed = 5;
    private boolean left = false;
    private boolean right = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }

        // 画面外に出ないように制限
        if (x < 0) x = 0;
        if (x > 360) x = 360;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    // ゲッター
    public int getX() { return x; }
    public int getY() { return y; }

    // 操作フラグのセッター
    public void setLeft(boolean left) { this.left = left; }
    public void setRight(boolean right) { this.right = right; }
}
