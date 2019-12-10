package GUI;

import GameLogic.Bullet;
import GameLogic.GameEngine;
import GameLogic.Ship;
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
    public Ship getShip(){
        return gameEngine.getShip();
    }
    public Bullet addBullet( int id, int x, int y, int dir){
        return gameEngine.addBullet( id, x, y, dir);
    }
    public ArrayList<Bullet> getBulletsList() {
        return gameEngine.getBulletsList();
    }
    public int getShipPosX(){
        return gameEngine.getShipPosX();
    }
    public int getShipPosY(){
        return gameEngine.getShipPosY();
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
