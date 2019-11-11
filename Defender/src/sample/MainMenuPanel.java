package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        soundSlider = new Slider(0, 100, 50);
        Image soundImage = new Image("sample/resources/sound3.png", 50, 50, false, true);
        ImageView soundImageView = new ImageView(soundImage);
        GridPane soundSliderWithImage = new GridPane();

        playButton.setStyle( "-fx-base: rgb(238,219,0)");
        exitButton.setStyle( "-fx-base: rgb(238,219,0)");
        playButton.setMinSize( 200, 100);
        helpButton.setMinSize( 200, 100);
        highScoresButton.setMinSize( 200, 100);
        exitButton.setMinSize( 200, 100);
        buttons.addRow(0, playButton, helpButton);
        buttons.addRow( 1, highScoresButton, exitButton);
        buttons.setLayoutX(200);
        buttons.setLayoutY(300);

        titleLabel.setFont(new Font("Thom", 40));
        titleLabel.setTextFill(Color.rgb(238,219,0));
        titleLabel.setPrefSize(200,100);
        titleLabel.setAlignment( Pos.CENTER);
        titleLabel.setLayoutX(300);
        titleLabel.setLayoutY(150);

        soundSliderWithImage.addRow(0, soundImageView, soundSlider);
        soundSliderWithImage.setPrefSize( 150, 50);
        soundSliderWithImage.setLayoutX(650);
        soundSliderWithImage.setLayoutY(0);

        this.getChildren().addAll( titleLabel, buttons, soundSliderWithImage);
        this.setBackground(new Background(background));
    }
}
