package GameLogic;

import GUI.ScreenManager;

import java.util.ArrayList;

public class GameEngine {
    private SoundManager robotMusic;
    private HighScoreManager highScoreManager;
    private MapManager mapManager;
    private ScreenManager screenManager;

    public GameEngine (){
        robotMusic = new SoundManager("/Defender/src/GUI/resources/robotmusic.wav");
        highScoreManager = new HighScoreManager();
        mapManager = new MapManager();
    }

    public void createWave(){
        mapManager.createWave();
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

    public int getShipLives(){
        return mapManager.getShipLives();
    }

    public void decreaseLives(){
        mapManager.decreaseLives();
    }

    public void nextWave(){
        mapManager.nextWave();
    }

    public int getWave(){
        return mapManager.getWaveNo();
    }
    public void addScore( int s){
        mapManager.addScore( s);
    }

    public int getScore(){
        return mapManager.getScore();
    }

    public int[] getMountains() {return mapManager.getMountains();}

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public void openMusic() { robotMusic.playSound(); }


    public void setVolume(double vol) { robotMusic.setVolume(vol);}

    public double getVolume() { return robotMusic.getVolume();}

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
