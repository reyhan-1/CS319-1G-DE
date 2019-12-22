package GameLogic;

public class Ship extends GameCharacter{
    public Ship ( int id, int x, int y){
        super( id, x, y, "GUI/resources/shipSprite.png", 100, 30);
        this.setVeloXMult(2);
        this.setVeloYMult(2);
    }

    public void move( int x, int y) {
        this.setPosX( this.getPosX() + 10 * x * this.getVeloXMult());
        this.setPosY( this.getPosY() + 10 * y * this.getVeloYMult());
    }
}
