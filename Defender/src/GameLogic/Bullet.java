package GameLogic;

public class Bullet extends GameCharacter{
    private int direction; // 1 if right, -1 if left
    private boolean owner; // true if ship, false if enemy

    public Bullet ( int id, int x, int y, int direction, boolean owner) {
        super(id, x, y, "GUI/resources/bullet2.png", 10, 5);
        this.direction = direction;
        this.owner = owner;
        this.setVeloXMult(15);
        this.setVeloYMult(15);
    }

    public int getDirection(){
        return direction;
    }

    public void move() {
        if(direction == 1){
            this.setPosX( this.getPosX() + this.getVeloXMult());
        }
        else{
            this.setPosX( this.getPosX() - this.getVeloXMult());
        }
    }
}
