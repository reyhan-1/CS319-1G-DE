package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class MainMenuPanel extends Pane {
    private Label titleLabel;
    private Button playButton;
    private Button helpButton;
    private Button highScoresButton;
    private Button exitButton;

    public Slider getSoundSlider() {
        return soundSlider;
    }

    public void setSoundSlider(Slider soundSlider) {
        this.soundSlider = soundSlider;
    }

    private Slider soundSlider;
    private ScreenManager screenManager;

    public MainMenuPanel(ScreenManager screenManager1) {
        screenManager = screenManager1;

        Image backgroundImage = new Image("GUI/resources/bg.png", 800, 600, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        this.setMinSize(800, 600);

        GridPane buttons = new GridPane();
        buttons.setHgap(20);
        buttons.setVgap(20);
        titleLabel = new Label("Defender");
        playButton = new Button("Play");
        helpButton = new Button("Help");
        highScoresButton = new Button("High \nScores");
        exitButton = new Button("Exit");
        
        double volume = screenManager.getGameEngine().getVolume();
        soundSlider = new Slider(0, 100, volume * 100);
        Image soundImage = new Image("GUI/resources/sound3.png", 50, 50, false, true);
        ImageView soundImageView = new ImageView(soundImage);
        GridPane soundSliderWithImage = new GridPane();

        String buttonStyle = "-fx-base: rgb(238,219,0); -fx-background-radius: 20";
        playButton.setStyle(buttonStyle);
        exitButton.setStyle(buttonStyle);
        highScoresButton.setStyle(buttonStyle);
        helpButton.setStyle(buttonStyle);

        playButton.setMinSize(200, 100);
        helpButton.setMinSize(200, 100);
        highScoresButton.setMinSize(200, 100);
        exitButton.setMinSize(200, 100);
        buttons.addRow(0, playButton, helpButton);
        buttons.addRow(1, highScoresButton, exitButton);
        buttons.setLayoutX(200);
        buttons.setLayoutY(300);

        getStylesheets().add(getClass().getResource("spaceFont.css").toExternalForm());
        titleLabel.setTextFill(Color.rgb(238, 219, 0));
        titleLabel.setPrefSize(600, 100);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(150);

        soundSliderWithImage.addRow(0, soundImageView, soundSlider);
        soundSliderWithImage.setPrefSize(150, 50);
        soundSliderWithImage.setLayoutX(650);
        soundSliderWithImage.setLayoutY(0);

        this.getChildren().addAll(titleLabel, buttons, soundSliderWithImage);
        this.setBackground(new Background(background));

        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.viewEnemies();
            }
        });
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.viewHelp( true);
            }
        });

        soundSlider.valueProperty().addListener((obs, o, n)->{
            double vol = new Double(soundSlider.getValue()) /100;
            screenManager.setVolume(Math.floor(vol * 100) / 100);
        });

        highScoresButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.viewHighScores();
            }
        });
    }

}
