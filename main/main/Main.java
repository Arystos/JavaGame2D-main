package main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Giuoco");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Size to fit the preferred size and layouts to subcomponents

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        
        gamePanel.setupGame(); // Load Game elements
        gamePanel.startGameThread();
    }

}