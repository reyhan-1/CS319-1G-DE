package GameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameCharacter {
    private int id;
    private Image sprite;
    private int posX, posY;
    private int width, height;
    private ImageView imageView;

    public GameCharacter (int id, int x, int y, String imageUrl, int imageW, int imageH){
        this.id = id;
        sprite = new Image( imageUrl, imageW, imageH, false, true);
        posX = x;
        posY = y;
        width = imageW;
        height = imageH;
        imageView = new ImageView( this.getSprite());
    }

    public void move( int x, int y){
        posX = posX + x;
        posY = posY + y;
    }

    public int getId(){ return id;}

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public Image getSprite(){
        return sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
