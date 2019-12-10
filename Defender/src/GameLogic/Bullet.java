package GameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends GameCharacter{
    private int direction; // 1 if right, -1 if left
    private ImageView imageView;
    public Bullet ( int id, int x, int y, int direction) {
        super(id, x, y, "GUI/resources/bullet2.png", 10, 3);
        this.direction = direction;
    }
    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    public int getDirection(){
        return direction;
    }
    public void setDirection( int i){
        direction = i;
    }
}
