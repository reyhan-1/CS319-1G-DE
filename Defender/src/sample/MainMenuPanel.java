package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenuPanel extends Pane {
    private Label titleLabel;
    private Button playButton;
    private Button helpButton;
    private Button highScoresButton;
    private Button exitButton;
    private Slider soundSlider;

    public MainMenuPanel(){
        Image backgroundImage = new Image("sample/resources/bg.png", 800, 600, false, true);
        BackgroundImage background = new BackgroundImage( backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        this.setMinSize(800,600);
        GridPane buttons = new GridPane();
        titleLabel = new Label( "Defender");
        playButton = new Button( "Play");
        helpButton = new Button( "Help");
        highScoresButton = new Button( "High Scores");
        exitButton = new Button( "Exit");
        titleLabel.setFont(new Font("Tahoma", 40));
        titleLabel.setTextFill(Color.rgb(238,219,0));
        playButton.setStyle( "-fx-base: rgb(238,219,0)");
        exitButton.setStyle( "-fx-base: rgb(238,219,0)");
        playButton.setMinSize( 200, 100);
        helpButton.setMinSize( 200, 100);
        highScoresButton.setMinSize( 200, 100);
        exitButton.setMinSize( 200, 100);
        buttons.addRow(0, playButton, helpButton);
        buttons.addRow( 1, highScoresButton, exitButton);

        titleLabel.setPrefSize(200,100);
        titleLabel.setAlignment( Pos.CENTER);
        titleLabel.setLayoutX(300);
        titleLabel.setLayoutY(150);
        buttons.setLayoutX(200);
        buttons.setLayoutY(300);
        this.getChildren().addAll( titleLabel, buttons);
        this.setBackground(new Background(background));

    }
}
