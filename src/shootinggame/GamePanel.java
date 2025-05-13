package shootinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private boolean isGameOver = false;
    private JButton restartButton;
    private JButton exitButton;
    private Random rand = new Random();
    private int score = 0; // スコア変数

    public GamePanel() {
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        setLayout(null); // ボタン配置のため

        player = new Player(200, 550);
        addButtons();
        startGame();
    }

    private void startGame() {
        enemies.clear();
        bullets.clear();
        score = 0; // スコア初期化
        enemies.add(new Enemy(rand.nextInt(360), 0));
        isGameOver = false;
        restartButton.setVisible(false);
        exitButton.setVisible(false);
        timer = new Timer(16, this);
        timer.start();
    }

    private void addButtons() {
        restartButton = new JButton("Restart");
        restartButton.setBounds(125, 300, 150, 40);
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> {
            player = new Player(200, 550);
            startGame();
            requestFocusInWindow();
        });

        exitButton = new JButton("Exit");
        exitButton.setBounds(125, 350, 150, 40);
        exitButton.setVisible(false);
        exitButton.addActionListener(e -> System.exit(0));

        add(restartButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", 80, 250);

            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("倒した敵の数：" + score, 110, 290);

            restartButton.setVisible(true);
            exitButton.setVisible(true);
            return;
        }

        player.draw(g);

        for (Bullet b : bullets) {
            b.draw(g);
        }

        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) return;

        player.move();

        // 弾の移動
        Iterator<Bullet> bIt = bullets.iterator();
        while (bIt.hasNext()) {
            Bullet b = bIt.next();
            b.move();
            if (b.isOffScreen()) bIt.remove();
        }

        // 敵の移動とゲームオーバー判定
        Iterator<Enemy> eIt = enemies.iterator();
        while (eIt.hasNext()) {
            Enemy enemy = eIt.next();
            enemy.move();
            if (enemy.y > 600) {
                isGameOver = true;
                timer.stop();
                repaint();
                return;
            }
        }

        // 弾と敵の当たり判定
        for (Iterator<Bullet> bi = bullets.iterator(); bi.hasNext();) {
            Bullet b = bi.next();
            for (Iterator<Enemy> ei = enemies.iterator(); ei.hasNext();) {
                Enemy enemy = ei.next();
                if (b.getBounds().intersects(enemy.getBounds())) {
                    bi.remove();
                    ei.remove();
                    score++; // スコア加算
                    enemies.add(new Enemy(rand.nextInt(360), 0));
                    break;
                }
            }
        }

        repaint();
    }

    @Override public void keyPressed(KeyEvent e) {
        if (isGameOver) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> player.setLeft(true);
            case KeyEvent.VK_RIGHT -> player.setRight(true);
            case KeyEvent.VK_SPACE -> {
                if (bullets.size() < 5) {
                    bullets.add(new Bullet(player.getX() + 18, player.getY()));
                }
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> player.setLeft(false);
            case KeyEvent.VK_RIGHT -> player.setRight(false);
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
}
