package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ScreenManager screenManager = new ScreenManager();
        primaryStage = screenManager.getMainStage();
        primaryStage.setTitle("Defender");
        primaryStage.show();

        screenManager.viewMainMenu();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
