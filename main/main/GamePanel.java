package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // TILE SETTINGS
    final int originalTileSize = 16; // 16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48

    // WINDOW SETTINGS
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player (this, keyHandler);
    public SuperObject object[] = new SuperObject [10]; // Display up to 10 objects at the same time

    public GamePanel () {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improove rendering performance (?)
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // Gamepanel is focussed to recive keyboard input
    }

    // Caricamento dei componenti di gioco
    public void setupGame() {
        assetSetter.setObject();

        playMusic(0);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Delta Movement
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // long timer = 0;
        // int drawcount = 0;
        
        while (gameThread != null) {

            currentTime = System.nanoTime();
            //timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;


            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                //drawcount ++;
            }

            /* 
            // show FPS on log screen
            if (timer >= 1000000000) {
                System.out.println("FPS: "+ drawcount);
                drawcount = 0;
                timer = 0;
            }
            */

        }
        
    }

    //Update Informaion
    public void update() {

        player.update();

    }

    //Draw the screen with the upload information
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileManager.draw(g2);

        //OBJECT
        for (int i = 0; i < object.length; i++) {
            if (object[i] != null) {
                object[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
