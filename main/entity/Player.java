package entity;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;

import javax.imageio.ImageIO;


public class Player extends Entity{

    GamePanel gp;
    main.KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player (GamePanel gp, main.KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        // Hitbox
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 16;
        

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

            // Directions
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISSION
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // IF COLLISION IS FALSE PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed;
                        break;
                    case "down": worldY += speed;
                        break;
                    case "left": worldX -= speed;
                        break;
                    case "right": worldX += speed;
                        break;
                
                    default:
                        break;
                }
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

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.object[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.object[i] = null; // Delete the object we touch from the screen
                    System.out.println("Key: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.object[i] = null;
                        hasKey--;
                        System.out.println("Door opened \nKey:" + hasKey);
                    }
                break;
            
                default:
                    break;
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
