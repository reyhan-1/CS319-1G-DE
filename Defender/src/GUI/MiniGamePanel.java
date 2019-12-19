package GUI;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class MiniGamePanel extends Pane {
    private ScreenManager screenManager;
    private Label scoreLabel, waveLabel, livesLabel;
    private Line line;
    public MiniGamePanel(ScreenManager sm){
        screenManager = sm;
        GridPane infoGrid = new GridPane();

        scoreLabel = new Label( "Score : " + screenManager.getScore());
        scoreLabel.setTextFill((Color.rgb(238, 219, 0)));
        scoreLabel.setFont( new Font(32));

        waveLabel = new Label( "Wave : " + screenManager.getWave());
        waveLabel.setTextFill((Color.rgb(238, 219, 0)));
        waveLabel.setFont( new Font(32));

        livesLabel = new Label( "Lives : " + screenManager.getShipLives());
        livesLabel.setTextFill((Color.rgb(238, 219, 0)));
        livesLabel.setFont( new Font(32));

        line = new Line( 0, 95, 800, 100);
        line.setStroke( Color.rgb(238, 219, 0));

        infoGrid.add( scoreLabel, 0, 0);
        infoGrid.add( livesLabel, 0, 1);
        // miniMap will be added here, to columnIndex 1
        infoGrid.add( waveLabel, 1, 0);
        infoGrid.setHgap( 300);

        this.getChildren().addAll( infoGrid, line);
        this.setMinSize( 800, 100);
        this.setStyle("-fx-background-color: black");
    }
    public void updateMiniScore(){
        scoreLabel.setText( "Score : " + screenManager.getScore());
    }

    public void updateMiniWave() {
        waveLabel.setText("Wave : " + screenManager.getWave());
    }

    public void updateMiniLives() {
        livesLabel.setText("Lives : " + screenManager.getShipLives());
    }
}
