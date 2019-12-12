package GUI;

import GameLogic.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenManager {
    private Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private GameEngine gameEngine;
    private GamePanel sGamePanel;

    public ScreenManager( GameEngine gameEngine1){
        mainPane = new Pane();
        mainScene = new Scene( mainPane, 800, 600);
        mainStage = new Stage();
        gameEngine = gameEngine1;
        mainStage.setScene( mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Ship addShip( int id, int x, int y) {
        return gameEngine.addShip( id, x, y);
    }

    public Enemy addEnemy( int id, int x, int y) {
        return gameEngine.addEnemy( id, x, y);
    }

    public Ship getShip(){
        return gameEngine.getShip();
    }

    public Bullet addBullet( int id, int x, int y, int dir, boolean owner){
        return gameEngine.addBullet( id, x, y, dir, owner);
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

    public int getShipPosX(){
        return gameEngine.getShipPosX();
    }

    public int getShipPosY(){
        return gameEngine.getShipPosY();
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

    public void viewMainMenu(){
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        mainPane.getChildren().add( mainMenuPanel);
    }

    public void viewPauseMenu(){
        Popup popup = new Popup();
        popup.setX( 300);
        popup.setY( 200);
        PauseMenuPanel pauseMenuPanel = new PauseMenuPanel( this);
        popup.getContent().add( pauseMenuPanel);

        popup.show( mainStage);

    }

    public void viewGame(){
        GamePanel gamePanel = null;
        try {
            gamePanel = new GamePanel(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add( gamePanel);
        sGamePanel = gamePanel;
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

    }

    public void viewHighScores(){

    }

    public void viewMiniMap(){

    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void changeTheme(){
        sGamePanel.changeTheme();
    }
}
