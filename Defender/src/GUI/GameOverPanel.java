
package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Window;

public class GameOverPanel extends Pane  {
    private ScreenManager screenManager;
    private Label gameOverLabel;
    private TextField username;
    private String initials = "";



    public GameOverPanel(ScreenManager sm)

    {
        screenManager = sm;

        this.setMinSize(300,200);
        this.setLayoutX( 230);
        this.setLayoutY( 50);

        gameOverLabel = new Label("GAME OVER");
        Font labelFont = new Font( "Arial", 22);
        gameOverLabel.setFont( labelFont);
        gameOverLabel.setStyle("-fx-text-fill: yellow");
        gameOverLabel.setLayoutX( 70);
        gameOverLabel.setLayoutY( 25);


        this.getChildren().add(gameOverLabel);

        Label gameOverLabel = new Label(" Welcome to the hall of fame.\nEnter your initials");
        gameOverLabel.setFont( labelFont);
        gameOverLabel.setStyle("-fx-text-fill: yellow");
        this.getChildren().add(gameOverLabel);

        gameOverLabel.setLayoutX( 30);
        gameOverLabel.setLayoutY( 70);


        username = new TextField();
        username.setLayoutX( 30);
        username.setLayoutY( 150);
        username.setEditable(true);
        username.setFont(new Font( "Arial", 40));
        username.setBackground(Background.EMPTY);

        username.setStyle("-fx-text-fill: yellow");
        this.getChildren().add(username);

        username.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(initials.length() < 4){
                    initials += ke.getCharacter();
                }
                if(initials.length() == 4){
                    //open highscore with initals
                    Window stage = username.getScene().getWindow();
                    stage.hide();
                    //sm.viewHighScores(initials.substring());
                }


            }
        });

    }


}
