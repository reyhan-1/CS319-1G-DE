package GameLogic;

import javafx.scene.image.Image;

public class Ship {
    private String name;
    private Image sprite;

    public Ship ( String shipName){
        name = shipName;
        sprite = new Image("GUI/resources/shipSprite.png", 50, 50, false, true);
    }

    public Image getSprite(){
        return sprite;
    }
}
