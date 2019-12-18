package GUI;


import GameLogic.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScreenManager {
    private Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private GameEngine gameEngine;

    private GamePanel gamePanel;
    private MiniGamePanel miniGamePanel;
    private GamePanelGroup gamePanelGroup;

    private MainMenuPanel mainMenuPanel;
    private Popup popupPause, popupHelp;

    public ScreenManager(GameEngine gameEngine1){
        mainPane = new Pane();
        mainScene = new Scene( mainPane, 800, 600);
        mainStage = new Stage();
        gameEngine = gameEngine1;
        popupPause = new Popup();
        popupHelp = new Popup();
        mainStage.setScene( mainScene);
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
        return mainScene;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public void setVolume(double vol) {
        gameEngine.setVolume(vol);
    }

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
        // mainPane.getChildren().clear();
        mainPane.getChildren().add( mainMenuPanel);
    }

    public void viewPauseMenu(){
        popupPause.setX( 300);
        popupPause.setY( 200);
        PauseMenuPanel pauseMenuPanel = new PauseMenuPanel( this);
        popupPause.getContent().add( pauseMenuPanel);

        popupPause.show( mainStage);

    }

    public MiniGamePanel getMiniGamePanel(){
        return miniGamePanel;
    }

    public void viewGame(){
        try {
            gamePanel = new GamePanel( this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        miniGamePanel = new MiniGamePanel( this);
        gamePanelGroup = new GamePanelGroup( this);

        mainPane.getChildren().clear();
        mainPane.getChildren().add( gamePanelGroup);
    }

    public void viewEnemies(){
        EnemiesPanel enemiesPanel = null;
        try {
            enemiesPanel = new EnemiesPanel(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainPane.getChildren().clear();
        mainPane.getChildren().add( enemiesPanel);
    }

    public void viewHelp(){
        popupHelp.setX( 300);
        popupHelp.setY( 200);
        HelpMenuPanel helpMenuPanel = new HelpMenuPanel( this);
        popupHelp.getContent().add( helpMenuPanel);

        popupHelp.show(mainStage);
    }
    public Popup getPopupHelp(){
        return popupHelp;
    }

    public Popup getPopupPause(){
        return popupPause;
    }

    public void viewHighScores(){

    }

    public void viewMiniMap(){

    }
}