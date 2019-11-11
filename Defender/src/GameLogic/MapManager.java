package GameLogic;

public class MapManager {
    private CollisionManager collisionManager;
    private int width;
    private int height;
    private Ship ship;

    public MapManager(){
        collisionManager = new CollisionManager();
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
}
