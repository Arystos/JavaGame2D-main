package main;

import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Font courier_40;

    public UI(GamePanel gp) {
        this.gp = gp;
        courier_40 = new Font("Courier", Font.PLAIN, 40);
    }

    public void draw (Graphics2D g2) {
        g2.setFont(courier_40);
    }
}
