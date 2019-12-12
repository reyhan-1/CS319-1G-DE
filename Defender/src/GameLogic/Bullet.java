package GameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends GameCharacter{
    private int direction; // 1 if right, -1 if left
    private boolean owner; // true if ship, false if enemy


    public Bullet ( int id, int x, int y, int direction, boolean owner) {
        super(id, x, y, "GUI/resources/bullet2.png", 10, 5);
        this.direction = direction;
        this.owner = owner;
    }

    public int getDirection(){
        return direction;
    }
    public void setDirection( int i){
        direction = i;
    }
}
