package GameLogic;

import GUI.ScreenManager;

import java.util.ArrayList;

public class GameEngine {
    private InputManager inputManager;
    private SoundManager soundManager;
    private HighScoreManager highScoreManager;

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    private MapManager mapManager;
    private ScreenManager screenManager;

    public GameEngine (){
        inputManager = new InputManager();
        soundManager = new SoundManager("/Defender/src/GUI/resources/robotmusic.wav");
        highScoreManager = new HighScoreManager();
        mapManager = new MapManager();
    }

    public Ship addShip( int id, int x, int y) {
        return mapManager.addShip( id, x, y);
    }

    public Enemy addEnemy( int id, int x, int y) {
        return mapManager.addEnemy( id, x, y);
    }

    public Bullet addBullet( int id, int x, int y, int dir, boolean owner){
        return mapManager.addBullet( id, x, y, dir, owner);
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void openMusic() { soundManager.playSound(); }

    public void setVolume(double vol) { soundManager.setVolume(vol);}

    public double getVolume() { return soundManager.getVolume();}

    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
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

    public Ship getShip(){
        return mapManager.getShip();
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
}
