package GUI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class HighScoresPanel extends Pane {
    private ScreenManager screenManager;
    private Label titleLabel;
    private Label hallOfFame;
    private Label scores;
    public HighScoresPanel(ScreenManager sm) throws InterruptedException{
        screenManager = sm;
        this.setPrefSize(800,600);

        Image backgroundImage = new Image("GUI/resources/bg.png", 800, 600, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);


        titleLabel = new Label("DEFENDER");
        Font titleFont = new Font( "Arial", 24);
        titleLabel.setFont( titleFont);
        titleLabel.setStyle("-fx-text-fill: yellow");
        titleLabel.setPrefSize( 200, 50);
        titleLabel.setLayoutX( 250);
        titleLabel.setLayoutY( 25);

        hallOfFame = new Label("HALL OF FAME");
        Font hallFont = new Font( "Arial", 19);
        hallOfFame.setFont( hallFont);
        hallOfFame.setStyle("-fx-text-fill: yellow");
        hallOfFame.setPrefSize( 200, 50);
        hallOfFame.setLayoutX( 250);
        hallOfFame.setLayoutY( 55);

        scores = new Label("TEST");
        scores.setFont( hallFont);
        scores.setStyle("-fx-text-fill: yellow");
        scores.setPrefSize( 700, 300);
        scores.setLayoutX( 250);
        scores.setLayoutY( 75);
        this.setBackground(new Background(background));
        this.getChildren().addAll( titleLabel, hallOfFame, scores);
    }

    public void setScores(String label){
        scores.setText(label);
    }
}
