package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class PauseMenuPanel extends Pane {
    private ScreenManager screenManager;
    private Label pauseLabel;
    private Button helpButton;
    private ToggleButton themeToggle;
    private Button quitButton;
    private Slider soundSlider;
    private Button continueButton;

    public PauseMenuPanel(ScreenManager sm){
        screenManager = sm;

        this.setMinSize(200,200);
        this.setLayoutX( 230);
        this.setLayoutY( 50);

        GridPane mainPane = new GridPane();
        GridPane buttons = new GridPane();

        pauseLabel = new Label("PAUSED");
        Font labelFont = new Font( "Arial", 24);
        pauseLabel.setFont( labelFont);
        pauseLabel.setStyle("-fx-text-fill: yellow");
        pauseLabel.setPrefSize( 100, 50);
        pauseLabel.setLayoutX( 70);
        pauseLabel.setLayoutY( 25);

        helpButton = new Button("Help");
        themeToggle = new ToggleButton("Change Theme");
        quitButton = new Button("Quit");
        continueButton = new Button("Continue");
        helpButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");
        themeToggle.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");
        quitButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");
        continueButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");

        buttons.addRow( 0, helpButton, themeToggle, quitButton, continueButton);
        buttons.setLayoutX( 15);
        buttons.setLayoutY( 100);
        buttons.setHgap( 10);
        buttons.setVgap( 10);
        soundSlider = new Slider(0, 100, 50);
        soundSlider.setPrefSize( 20, 20);
        soundSlider.setLayoutX( 180);
        soundSlider.setLayoutY( 0);

        this.getChildren().addAll(pauseLabel, buttons, soundSlider);

        this.setBackground( new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle("-fx-border-color: yellow");

        themeToggle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.changeTheme();
            }
        });

        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.viewHelp();
            }
        });

        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        continueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Window stage = continueButton.getScene().getWindow();
                stage.hide();
            }
        });

        soundSlider.valueProperty().addListener((obs, o, n)->{
            double vol = new Double(soundSlider.getValue()) /100;
            screenManager.setVolume(Math.floor(vol * 100) / 100);
        });


    }


}
