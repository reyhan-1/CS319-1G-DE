package GameLogic;

import GUI.ScreenManager;

import java.util.ArrayList;

public class GameEngine {
    private InputManager inputManager;
    private SoundManager soundManager;
    private HighScoreManager highScoreManager;
    private MapManager mapManager;
    private ScreenManager screenManager;

    public GameEngine (){
        inputManager = new InputManager();
        soundManager = new SoundManager();
        highScoreManager = new HighScoreManager();
        mapManager = new MapManager();
    }

    public Ship addShip( int id, int x, int y) {
        return mapManager.addShip( id, x, y);
    }

    public Enemy addEnemy( int id, int x, int y) {
        return mapManager.addEnemy( id, x, y);
    }

    public Ship getShip(){
        return mapManager.getShip();
    }

    public Bullet addBullet( int id, int x, int y, int dir, boolean owner){
        return mapManager.addBullet( id, x, y, dir, owner);
    }
    public ArrayList<Bullet> getBulletsListS() {
        return mapManager.getBulletsListS();
    }
    public ArrayList<Bullet> getBulletsListE() {
        return mapManager.getBulletsListE();
    }

    public ArrayList<Ship> getShipsList() {
        return mapManager.getShipsList();
    }

    public ArrayList<Enemy> getEnemiesList() {
        return mapManager.getEnemiesList();
    }

    public ArrayList<GameCharacter> checkCollisionB_E(ArrayList<Bullet> list1,
                                                   ArrayList<Enemy> list2){
        return mapManager.checkCollisionB_E( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionB_S(ArrayList<Bullet> list1,
                                                   ArrayList<Ship> list2){
        return mapManager.checkCollisionB_S( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionS_E(ArrayList<Ship> list1,
                                                   ArrayList<Enemy> list2){
        return mapManager.checkCollisionS_E( list1, list2);
    }



    public InputManager getInputManager() {
        return inputManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public int getShipPosX() {
        return mapManager.getShip().getPosX();
    }

    public int getShipPosY() {
        return mapManager.getShip().getPosY();
    }
}
