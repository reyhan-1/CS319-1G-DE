package GUI;


import GameLogic.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScreenManager {
    private Stage mainStage;
    private GameEngine gameEngine;
    private GamePanel gamePanel;
    private MiniGamePanel miniGamePanel;
    private GamePanelGroup gamePanelGroup;
    private PauseMenuPanel pauseMenuPanel;
    private Scene gameScene;
    private MainMenuPanel mainMenuPanel;

    public ScreenManager(GameEngine gameEngine1){
        gameEngine = gameEngine1;
        mainStage = new Stage();
        viewMainMenu();
        gameEngine.openMusic();
        gameEngine.setVolume( 0.30);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public Ship addShip( int id, int x, int y) {
        return gameEngine.addShip( id, x, y);
    }

    public Enemy addEnemy( int id, int x, int y) {
        return gameEngine.addEnemy( id, x, y);
    }

    public Bullet addBullet( int id, int x, int y, int dir, boolean owner){
        return gameEngine.addBullet( id, x, y, dir, owner);
    }

    public void addScore( int s){
        gameEngine.addScore( s);
    }

    public void updateMiniScore(){
        getMiniGamePanel().updateMiniScore();
    }

    public void updateMiniWave(){
        getMiniGamePanel().updateMiniWave();
    }

    public void updateMiniLives() {
        getMiniGamePanel().updateMiniLives();
    }

    public void updateMiniShipCoords(){
        getMiniGamePanel().updateShipPosition();
    }

    public void updateMiniEnemyCoords(){
        getMiniGamePanel().updateEnemyPositions();
    }

    public int getScore(){
        return gameEngine.getScore();
    }

    public ArrayList<GameCharacter> checkCollisionB_E(ArrayList<Bullet> list1,
                                                      ArrayList<Enemy> list2){
        return gameEngine.checkCollisionB_E( list1, list2);
    }

    public ArrayList<GameCharacter> checkCollisionB_S(ArrayList<Bullet> list1,
                                                      ArrayList<Ship> list2){
        return gameEngine.checkCollisionB_S( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionS_E(ArrayList<Ship> list1,
                                                      ArrayList<Enemy> list2){
        return gameEngine.checkCollisionS_E( list1, list2);
    }

    public Ship getShip(){
        return gameEngine.getShip();
    }

    public ArrayList<Bullet> getBulletsListS() {
        return gameEngine.getBulletsListS();
    }

    public ArrayList<Bullet> getBulletsListE() {
        return gameEngine.getBulletsListE();
    }

    public ArrayList<Ship> getShipsList() {
        return gameEngine.getShipsList();
    }

    public ArrayList<Enemy> getEnemiesList() {
        return gameEngine.getEnemiesList();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public Scene getMainScene() {
        return mainStage.getScene();
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public void setVolume(double vol) {
        gameEngine.setVolume(vol);
    }

    public int[] getMountains() { return gameEngine.getMountains(); }

    public void stopAnimations(){
        gamePanel.stopAnimations();
    }

    public void setGameEngine( GameEngine gE) {
        gameEngine = gE;
    }

    public void changeTheme(){
        gamePanel.changeTheme();
    }

    public void viewMainMenu(){
        mainMenuPanel = new MainMenuPanel(this);
        Scene newScene = new Scene( mainMenuPanel, 800, 600);
        mainStage.setScene( newScene);
    }

    public void viewPauseMenu(){
        gamePanel.stopAnimations();
        pauseMenuPanel = new PauseMenuPanel( this);
        Scene newScene = new Scene( pauseMenuPanel, 800, 600);
        mainStage.setScene( newScene);
        pauseMenuPanel.addKeyHandler();
    }

    public MiniGamePanel getMiniGamePanel(){
        return miniGamePanel;
    }

    public void createWave(){
        gameEngine.createWave();
    }

    public int getWave(){
        return gameEngine.getWave();
    }

    public void nextWave(){
        gameEngine.nextWave();
    }
    public void viewGame( boolean resumed){
        if ( resumed){
            gamePanel.startAnimations();
            mainStage.setScene( gameScene);
        }
        else {
            try {
                gamePanel = new GamePanel(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            miniGamePanel = new MiniGamePanel(this);
            gamePanelGroup = new GamePanelGroup(this);

            gameScene = new Scene(gamePanelGroup, 800, 600);
            mainStage.setScene( gameScene);
        }
        gamePanel.addHandler();
    }

    public void viewEnemies(){
        EnemiesPanel enemiesPanel = null;
        try {
            enemiesPanel = new EnemiesPanel(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scene newScene = new Scene( enemiesPanel, 800, 600);
        mainStage.setScene( newScene);
    }
    public void viewGameOver(int currentScore){
        GameOverPanel gameOverPanel = new GameOverPanel(this, currentScore);
        Scene newScene = new Scene( gameOverPanel, 800, 600);
        mainStage.setScene( newScene);
        gameOverPanel.addKeyHandler();
    }

    public void viewHelp( boolean mainHelp){
        HelpMenuPanel helpMenuPanel = new HelpMenuPanel( this, mainHelp);
        Scene newScene = new Scene( helpMenuPanel, 800, 600);
        mainStage.setScene( newScene);
    }

    public void viewHighScores(){
        HighScoresPanel highScoresPanel = null;
        try {
            String scoresLabel = gameEngine.getHighScoreManager().getLabel();
            highScoresPanel = new HighScoresPanel(this);
            highScoresPanel.setScores(scoresLabel);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scene newScene = new Scene( highScoresPanel, 800, 600);
        mainStage.setScene( newScene);
    }


    public int getShipLives(){
        return gameEngine.getShipLives();
    }

    public void decreaseLives(){
        gameEngine.decreaseLives();
    }
}