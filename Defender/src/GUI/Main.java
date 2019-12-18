package GUI;

import GameLogic.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameEngine gameEngine = new GameEngine();
        ScreenManager screenManager = new ScreenManager(gameEngine);
        primaryStage = screenManager.getMainStage();
        primaryStage.setTitle("Defender");
        primaryStage.show();
        screenManager.viewMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
