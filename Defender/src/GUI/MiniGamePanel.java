package GUI;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class MiniGamePanel extends Pane {
    private ScreenManager screenManager;
    private Label scoreLabel;
    private Line line;
    public MiniGamePanel(ScreenManager sm){
        screenManager = sm;
        scoreLabel = new Label( "Score : " + screenManager.getScore());
        scoreLabel.setTextFill((Color.rgb(238, 219, 0)));
        scoreLabel.setFont( new Font(32));
        line = new Line( 0, 95, 800, 100);
        line.setStroke( Color.rgb(238, 219, 0));

        this.getChildren().addAll( scoreLabel, line);
        this.setMinSize( 800, 100);
        this.setStyle("-fx-background-color: black");
    }
    public void updateMiniScore(){
        scoreLabel.setText( "Score : " + screenManager.getScore());
    }
}
