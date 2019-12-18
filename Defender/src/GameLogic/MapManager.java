package GameLogic;

import java.util.ArrayList;

public class MapManager {
    private CollisionManager collisionManager;
    private int width;
    private int height;
    private Ship ship;
    private ArrayList<Ship> shipsList; // for practical reasons
    private ArrayList<Bullet> bulletsListE, bulletsListS; // separate lists for ship and enemy bullets
    private ArrayList<Enemy> enemiesList;

    public MapManager(){
        bulletsListE = new ArrayList<Bullet>();
        bulletsListS = new ArrayList<Bullet>();
        enemiesList = new ArrayList<Enemy>();
        shipsList = new ArrayList<Ship>();
        collisionManager = new CollisionManager();
    }

    public Ship addShip(int id, int x, int y) {
        ship = new Ship( id, x, y);
        shipsList.add( ship);
        return ship;
    }

    public Enemy addEnemy(int id, int x, int y) {
        Enemy enemy = new Enemy( id, x, y);
        enemiesList.add( enemy);
        return enemy;
    }

    public Bullet addBullet( int id, int x, int y, int dir, boolean owner){
        Bullet bullet = new Bullet( id, x, y, dir, owner);
        if ( owner){
            bulletsListS.add( bullet);
        }
        else{
            bulletsListE.add( bullet);
        }
        return bullet;
    }

    public ArrayList<Ship> getShipsList() {
        return shipsList;
    }

    public ArrayList<Enemy> getEnemiesList() {
        return enemiesList;
    }

    public Ship getShip() {
        return ship;
    }

    public ArrayList<Bullet> getBulletsListS() {
        return bulletsListS;
    }

    public ArrayList<Bullet> getBulletsListE() {
        return bulletsListE;
    }

    public ArrayList<GameCharacter> checkCollisionB_E(ArrayList<Bullet> list1,
                                                   ArrayList<Enemy> list2){
        return collisionManager.getDestroyedB_E( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionB_S(ArrayList<Bullet> list1,
                                                      ArrayList<Ship> list2){
        return collisionManager.getDestroyedB_S( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionS_E(ArrayList<Ship> list1,
                                                      ArrayList<Enemy> list2){
        return collisionManager.getDestroyedS_E( list1, list2);
    }
}