package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        //KEYS
        gp.object[0] = new OBJ_Key();
        gp.object[0].worldX = 23 * gp.tileSize;
        gp.object[0].worldY = 7 * gp.tileSize;

        gp.object[1] = new OBJ_Key();
        gp.object[1].worldX = 23 * gp.tileSize;
        gp.object[1].worldY = 40 * gp.tileSize;

        gp.object[2] = new OBJ_Key();
        gp.object[2].worldX = 38 * gp.tileSize;
        gp.object[2].worldY = 8 * gp.tileSize;

        //DOORS
        gp.object[3] = new OBJ_Door();
        gp.object[3].worldX = 10 * gp.tileSize;
        gp.object[3].worldY = 11 * gp.tileSize;

        gp.object[4] = new OBJ_Door();
        gp.object[4].worldX = 8 * gp.tileSize;
        gp.object[4].worldY = 28 * gp.tileSize;

        gp.object[5] = new OBJ_Door();
        gp.object[5].worldX = 12 * gp.tileSize;
        gp.object[5].worldY = 22 * gp.tileSize;

        //CHESTS
        gp.object[6] = new OBJ_Chest();
        gp.object[6].worldX = 10 * gp.tileSize;
        gp.object[6].worldY = 8 * gp.tileSize;

        //BOOTS
        gp.object[7] = new OBJ_Boots();
        gp.object[7].worldX = 37 * gp.tileSize;
        gp.object[7].worldY = 42 * gp.tileSize;

    }
    
}
