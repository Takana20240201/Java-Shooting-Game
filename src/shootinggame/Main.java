package shootinggame;

import javax.swing.*;

// エントリーポイント
// ウィンドウ表示・ゲーム開始
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Shooting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
