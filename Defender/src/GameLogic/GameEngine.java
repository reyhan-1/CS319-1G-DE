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
}
