package GameLogic;

import java.util.Random;

public class Enemy extends GameCharacter{
    private int direction; // 0 for up, 1 for down, 2 for left, 3 for right
    private int speed;
    public Enemy ( int id, int x, int y){
        super( id, x, y, "GUI/resources/bomber.png", 20, 20);
        speed = 2;
        direction = 1;
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void followShip( Ship ship){
        int shipX = ship.getPosX();
        int shipY = ship.getPosY();
        int enemyX = this.getPosX();
        int enemyY = this.getPosY();
        int xDiff = shipX - enemyX;
        int yDiff = shipY - enemyY;
        boolean enemyInSight = Math.abs( xDiff) < 400;
        Random random = new Random();
        boolean randomizeEnemyDirection = random.nextBoolean();
        if ( enemyInSight) {
            if (randomizeEnemyDirection == true) {
                if (enemyY != shipY) {
                    if (yDiff > speed){
                        this.move( 0, speed);
                    }
                    else if ( yDiff < (-1)*speed) {
                        this.move(0, (-1)*speed);
                    }
                    else {
                        this.move(0, yDiff);
                    }
                }
                else {
                    if ( xDiff > speed){
                        this.move( speed, 0);
                    }
                    else if ( xDiff < (-1)*speed) {
                        this.move( (-1)*speed, 0);
                    }
                    else {
                        this.move(xDiff, 0);
                    }
                }
            }
            else {
                if (enemyX != shipX) {
                    if ( xDiff > speed){
                        this.move(speed, 0);
                    }
                    else if ( xDiff < (-1)*speed) {
                        this.move((-1)*speed, 0);
                    }
                    else {
                        this.move(xDiff, 0);
                    }
                }
                else {
                    if (yDiff > speed) {
                        this.move(0, speed);
                    }
                    else if ( yDiff < (-1)*speed) {
                        this.move(0, (-1)*speed);
                    }
                    else {
                        this.move(0, yDiff);
                    }
                }
            }
        }
    }
}
