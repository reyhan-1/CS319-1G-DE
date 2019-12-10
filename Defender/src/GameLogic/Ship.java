package GameLogic;

import javafx.scene.image.Image;

public class Ship extends GameCharacter{

    public Ship ( int id, int x, int y){
        super( id, x, y, "GUI/resources/shipSprite.png", 50, 50);
    }
}
