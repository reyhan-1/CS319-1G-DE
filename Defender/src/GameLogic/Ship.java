package GameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends GameCharacter{
    public Ship ( int id, int x, int y){
        super( id, x, y, "GUI/resources/shipSprite.png", 100, 30);
    }
}
