/*package GameLogic;

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
*/
package GameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameCharacter {
    private int id;
    private Image sprite;
    private int posX, posY;
    private int width, height;
    private ImageView imageView;
    private int veloXMult;
    private int veloYMult;
    private int direction;

    public GameCharacter (int id, int x, int y, String imageUrl, int imageW, int imageH){
        this.id = id;
        sprite = new Image( imageUrl, imageW, imageH, false, true);
        posX = x;
        posY = y;
        width = imageW;
        height = imageH;
        imageView = new ImageView( this.getSprite());
        direction = 1;
    }

    public int getId(){ return id;}

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    public int getVeloXMult() { return veloXMult; }

    public int getVeloYMult() { return veloYMult;}

    public void setVeloXMult(int veloXMult) {
        this.veloXMult = veloXMult;
    }

    public void setVeloYMult(int veloYMult) {
        this.veloYMult = veloYMult;
    }

    public int getDirection(){ return direction; }

    public void setDirection(int direction) { this.direction = direction; }
}
