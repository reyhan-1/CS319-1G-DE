package GUI;

import GameLogic.GameEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PauseMenuPanel extends Pane {
    private ScreenManager screenManager;
    private Label pauseLabel;
    private Button helpButton;
    private ToggleButton themeToggle;
    private Button quitButton;
    private Slider soundSlider;

    public PauseMenuPanel(ScreenManager sm){
        screenManager = sm;

        this.setMinSize(200,200);
        this.setLayoutX( 300);
        this.setLayoutY( 200);

        GridPane mainPane = new GridPane();
        GridPane buttons = new GridPane();

        pauseLabel = new Label("PAUSE");
        pauseLabel.setLayoutX( 100);
        pauseLabel.setLayoutY( 20);

        helpButton = new Button("Help");
        themeToggle = new ToggleButton("Change Theme");
        quitButton = new Button("Quit");
        buttons.addRow( 0, helpButton, themeToggle, quitButton);
        buttons.setLayoutX( 100);
        buttons.setLayoutY( 150);

        soundSlider = new Slider(0, 100, 50);
        soundSlider.setPrefSize( 20, 20);
        soundSlider.setLayoutX( 180);
        soundSlider.setLayoutY( 0);

        this.getChildren().addAll( pauseLabel, buttons, soundSlider);

        this.setBackground( new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }


}
