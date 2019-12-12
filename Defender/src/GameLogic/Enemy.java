package GameLogic;

import javafx.scene.image.ImageView;

public class Enemy extends GameCharacter{

    public Enemy ( int id, int x, int y){
        super( id, x, y, "GUI/resources/bomber.png", 20, 20);
        ImageView enemyImageView = new ImageView( this.getSprite());
        setImageView( enemyImageView);
    }
}
