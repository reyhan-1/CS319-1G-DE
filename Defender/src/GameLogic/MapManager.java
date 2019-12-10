package GameLogic;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MapManager {
    private CollisionManager collisionManager;
    private int width;
    private int height;
    private Ship ship;
    private ArrayList<Bullet> bulletsList;

    public MapManager(){
        bulletsList = new ArrayList<Bullet>();
        collisionManager = new CollisionManager();
    }
    public Ship addShip(int id, int x, int y) {
        ship = new Ship( id, x, y);
        return ship;
    }
    public Bullet addBullet( int id, int x, int y, int dir){
        Bullet bullet = new Bullet( id, x, y, dir);
        bulletsList.add( bullet);
        return bullet;
    }
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public Ship getShip() {
        return ship;
    }
    public ArrayList<Bullet> getBulletsList() {
        return bulletsList;
    }
}
