
package GUI;

import GameLogic.MapManager;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameOverPanel extends Pane {
    private ScreenManager screenManager;
    private int currScore;
    private Label gameOverLabel, enterLabel;
    private TextField username;
    private String initials = "";
    private String toShow = "";


    public GameOverPanel(ScreenManager sm, int currentScore)
    {
        screenManager = sm;
        currScore = currentScore;
        GridPane mainPane = new GridPane();
        mainPane.setPrefSize( 600, 200);
        mainPane.setLayoutX( 100);
        mainPane.setLayoutY( 200);

        this.setBackground( new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle("-fx-border-color: yellow");

        gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setStyle("-fx-text-fill: yellow");
        mainPane.setHalignment(gameOverLabel, HPos.CENTER);

        enterLabel = new Label("Enter your initials below:");
        enterLabel.setStyle("-fx-text-fill: yellow");
        enterLabel.setAlignment( Pos.CENTER);

        username = new TextField();
        username.setEditable(true);
        username.setBackground(Background.EMPTY);
        username.setStyle("-fx-text-fill: yellow");
        username.setAlignment( Pos.CENTER);

        getStylesheets().add(getClass().getResource("spaceFontGameOver.css").toExternalForm());

        mainPane.addRow(0, gameOverLabel);
        mainPane.addRow( 1, enterLabel);
        mainPane.addRow(2, username);
        this.getChildren().add( mainPane);

        username.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if( username.getLength() < 4){
                    initials += ke.getCharacter();
                }
                else{
                    username.setText( username.getText().substring(0, 3));
                    username.positionCaret( 3);
                }
            }
        });
    }

    public void addKeyHandler(){
        screenManager.getMainScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if ( username.getLength() > 0) {
                    screenManager.getGameEngine().getHighScoreManager().addScore(initials, currScore);
                    // add high score with initials and score variables
                    screenManager.getGamePanel().stopAnimations();
                    screenManager.getGamePanel().removeKeyHandler();
                    screenManager.getGameEngine().setMapManager( new MapManager());
                    screenManager.viewHighScores();
                }
            }
        });
    }
}
