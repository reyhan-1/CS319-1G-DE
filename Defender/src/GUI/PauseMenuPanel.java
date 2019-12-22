package GUI;

import GameLogic.MapManager;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PauseMenuPanel extends Pane {
    private ScreenManager screenManager;
    private Label pauseLabel;
    private Button helpButton;
    private Button quitButton;
    private Slider soundSlider;
    private Button continueButton;
    private Boolean nightBackground;

    public PauseMenuPanel(ScreenManager sm){
        screenManager = sm;

        GridPane mainPane = new GridPane();
        GridPane buttons = new GridPane();
        mainPane.setPrefSize( 400, 200);
        mainPane.setLayoutX( 250);
        mainPane.setLayoutY( 200);

        getStylesheets().add(getClass().getResource("spaceFontGameOver.css").toExternalForm());

        pauseLabel = new Label("PAUSED");
        pauseLabel.setStyle("-fx-text-fill: yellow");
        pauseLabel.setPrefSize( 300, 50);
        pauseLabel.setAlignment( Pos.CENTER);


        helpButton = new Button("Help");
        quitButton = new Button("Quit");
        continueButton = new Button("Continue");
        helpButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");
        quitButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");
        continueButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");

        buttons.addRow( 0, helpButton, quitButton, continueButton);
        buttons.setHgap( 10);
        buttons.setVgap( 10);
        buttons.setAlignment( Pos.CENTER);

        soundSlider = screenManager.getMainMenuPanel().getSoundSlider();
        soundSlider.setPrefSize( 50, 50);
        soundSlider.setLayoutX( 740);
        soundSlider.setLayoutY( 0);

        mainPane.addRow( 0, pauseLabel);
        mainPane.addRow( 1, buttons);

        this.getChildren().addAll( mainPane, soundSlider);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle("-fx-border-color: yellow");

        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.viewHelp( false);
            }
        });

        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.getGamePanel().stopAnimations();
                screenManager.getGamePanel().removeKeyHandler();
                screenManager.getGameEngine().setMapManager( new MapManager());
                screenManager.viewMainMenu();
            }
        });

        continueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                screenManager.viewGame( true); // game is resumed
            }
        });

        soundSlider.valueProperty().addListener((obs, o, n)->{
            double vol = new Double(soundSlider.getValue()) /100;
            screenManager.setVolume(Math.floor(vol * 100) / 100);
        });


    }

    public void addKeyHandler(){
        screenManager.getMainScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                screenManager.viewGame( true);
            }
        });
    }
}
