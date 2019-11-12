package GameLogic;

public class MapManager {
    private CollisionManager collisionManager;
    private int width;
    private int height;
    private Ship ship;

    public MapManager(){
        collisionManager = new CollisionManager();
    }
    public Ship addShip(String shipName) {
        ship = new Ship( shipName);
        return ship;
    }
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public Ship getShip() {
        return ship;
    }
}
