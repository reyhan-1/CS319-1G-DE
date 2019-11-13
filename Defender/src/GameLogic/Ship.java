package GameLogic;

import javafx.scene.image.Image;

public class Ship {
    private String name;
    private Image sprite;
    private int posX, posY;

    public Ship ( String shipName, int x, int y){
        name = shipName;
        sprite = new Image("GUI/resources/shipSprite.png", 50, 50, false, true);
        posX = x;
        posY = y;
    }

    public void move( int x, int y){
        posX = posX + x;
        posY = posY + y;
    }
    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }
    public Image getSprite(){
        return sprite;
    }
}
