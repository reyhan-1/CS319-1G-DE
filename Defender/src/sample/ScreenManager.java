package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenManager {
    private Pane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ScreenManager(){
        mainPane = new Pane();
        mainScene = new Scene( mainPane, 800, 600);
        mainStage = new Stage();
        mainStage.setScene( mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public void viewMainMenu(){
        MainMenuPanel mainMenuPanel = new MainMenuPanel();
        mainPane.getChildren().add( mainMenuPanel);
    }

    public void viewPauseMenu(){

    }

    public void viewGame(){

    }

    public void viewEnemies(){

    }

    public void viewHelp(){

    }

    public void viewHighScores(){

    }

    public void viewMiniMap(){

    }
}
