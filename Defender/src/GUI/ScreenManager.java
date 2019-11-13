package GUI;

import GameLogic.GameEngine;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenManager {
    private Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private GameEngine gameEngine;

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

    public void viewMainMenu(){
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        mainPane.getChildren().add( mainMenuPanel);
    }

    public void viewPauseMenu(){

    }

    public void viewGame(){
        GamePanel gamePanel = new GamePanel(this);
        mainPane.getChildren().clear();
        mainPane.getChildren().add( gamePanel);
    }

    public void viewEnemies(){

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
}
