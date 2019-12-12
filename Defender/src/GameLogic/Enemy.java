package GameLogic;

import javafx.scene.image.ImageView;

public class Enemy extends GameCharacter{
    private int direction; // 0 for up, 1 for down, 2 for left, 3 for right
    public Enemy ( int id, int x, int y){
        super( id, x, y, "GUI/resources/bomber.png", 20, 20);
        direction = 1;
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
