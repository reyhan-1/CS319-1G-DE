package GameLogic;

import GUI.ScreenManager;

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

    public Ship addShip(String shipName, int x, int y) {
        return mapManager.addShip(shipName, x, y);
    }

    public Ship getShip(){
        return mapManager.getShip();
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
