package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Magikey.png"));
        } catch (IOException e) {
            System.out.println("Errore nel caricamento delle immagini Objects");
            e.printStackTrace();
        }

    }
}
