package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest() {

        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Treasure_chest.png"));
        } catch (IOException e) {
            System.out.println("Errore nel caricamento delle immagini Objects");
            e.printStackTrace();
        }

    }
}
