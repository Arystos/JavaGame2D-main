package entity;

import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.awt.Rectangle;

import javax.imageio.ImageIO;


public class Player extends Entity{

    GamePanel gp;
    main.KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0; // Buffer to stay still

    public Player (GamePanel gp, main.KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        // Hitbox
        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 16;
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_culo1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_culo2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_culo3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_sotto1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_sotto3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_sx1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_sx2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_sx3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_dx1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Rapto_dx3.png"));
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
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else { // If player is still
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 2; // Use sprite [2] (Still sprite)
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.object[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.object[i] = null; // Delete the object we touch from the screen
                    if (hasKey == 1) {
                        gp.ui.showMessage("You got a key!");
                    } else if (hasKey == 2) {
                        gp.ui.showMessage("You got another key!");
                    } else if (hasKey == 3) {
                        gp.ui.showMessage("Enough! Find the treasure");
                    }
                    
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.object[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door opened!");
                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                break;
                case "Boots":
                    gp.playSoundEffect(2);
                    speed += 1;
                    gp.object[i] = null;
                    gp.ui.showMessage("SPEEEEED!");
                break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
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
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // Show Collision Shape
        /* 
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        */
    }
}
