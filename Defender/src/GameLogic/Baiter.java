package GameLogic;

import java.util.Random;

public class Baiter extends Enemy{
    private Ship ship;
    public Baiter(int id, int x, int y, Ship ship) {
        super(id, x, y, "GUI/resources/baiter.png", 200);
        this.setVeloXMult( 5);
        this.setVeloYMult( 5);
        this.setShip(ship);
    }

    @Override
    public void move(){
        int shipX = ship.getPosX();
        int shipY = ship.getPosY();
        int enemyX = this.getPosX();
        int enemyY = this.getPosY();
        int xDiff = shipX - enemyX;
        int yDiff = shipY - enemyY;
        int yVelo = this.getVeloYMult();
        int xVelo = this.getVeloXMult();
        boolean enemyInSight = Math.abs( xDiff) < 1600;
        Random random = new Random();
        boolean randomizeEnemyDirection = random.nextBoolean();
        if ( enemyInSight) {
            if (randomizeEnemyDirection) {
                if (enemyY != shipY) {
                    if (yDiff > yVelo){
                        this.setPosY( this.getPosY() + yVelo);
                    }
                    else if ( yDiff < (-1)*yVelo) {
                        this.setPosY(this.getPosY() - yVelo);
                    }
                    else {
                        this.setPosY(this.getPosY() + yDiff);
                    }
                }
                else {
                    if ( xDiff > xVelo){
                        this.setPosX( this.getPosX() + xVelo);
                        this.setDirection(1);
                    }
                    else if ( xDiff < (-1)*xVelo) {
                        this.setPosX( this.getPosX() - xVelo);
                        this.setDirection(0);
                    }
                    else {
                        this.setPosX( this.getPosX() + xDiff);
                    }
                }
            }
            else {
                if (enemyX != shipX) {
                    if ( xDiff > xVelo){
                        this.setPosX( this.getPosX() + xVelo);
                    }
                    else if ( xDiff < (-1)*xVelo) {
                        this.setPosX( this.getPosX() - xVelo);
                    }
                    else {
                        this.setPosX( this.getPosX() + xDiff);
                    }
                }
                else {
                    if (yDiff > yVelo){
                        this.setPosY( this.getPosY() + yVelo);
                    }
                    else if ( yDiff < (-1)*yVelo) {
                        this.setPosY(this.getPosY() - yVelo);
                    }
                    else {
                        this.setPosY(this.getPosY() + yDiff);
                    }
                }
            }
        }
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
