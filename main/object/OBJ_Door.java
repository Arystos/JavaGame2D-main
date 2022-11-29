package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {

        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/porta.png"));
        } catch (IOException e) {
            System.out.println("Errore nel caricamento delle immagini Objects");
            e.printStackTrace();
        }

        collision = true;
        
    }
}
