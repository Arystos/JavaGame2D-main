package entity;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Entity{

    GamePanel gp;
    main.KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player (GamePanel gp, main.KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update () {

        // When a move key is pressed
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            // Move
            if (keyHandler.upPressed) {
                direction = "up";
                worldY -= speed;
                //worldY = worldY - speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                worldX += speed;
            }
    
            // Update Character Frames every 10 frames when movi
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        

    }

    public void draw (Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
