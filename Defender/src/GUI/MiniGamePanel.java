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
    private MiniMapPanel miniMapPanel;
    public MiniGamePanel(ScreenManager sm){
        screenManager = sm;
        GridPane infoGrid = new GridPane();

        scoreLabel = new Label( "Score : " + screenManager.getScore());
        scoreLabel.setTextFill((Color.rgb(238, 219, 0)));
        scoreLabel.setFont( new Font(24));

        waveLabel = new Label( "Wave : " + screenManager.getWave());
        waveLabel.setTextFill((Color.rgb(238, 219, 0)));
        waveLabel.setFont( new Font(24));

        livesLabel = new Label( "Lives : " + screenManager.getShipLives());
        livesLabel.setTextFill((Color.rgb(238, 219, 0)));
        livesLabel.setFont( new Font(24));

        line = new Line( 0, 95, 800, 100);
        line.setStroke( Color.rgb(238, 219, 0));

        miniMapPanel = new MiniMapPanel( screenManager);

        infoGrid.add( scoreLabel, 0, 0);
        infoGrid.add( livesLabel, 0, 1);
        infoGrid.add( miniMapPanel, 1,0);
        infoGrid.add( waveLabel, 2, 0);
        infoGrid.setHgap( 50);
        infoGrid.setVgap( 0);
        infoGrid.setMaxSize( 800, 95);
        this.getChildren().addAll( infoGrid, line);
        this.setMaxSize( 800, 100);
        this.setStyle("-fx-background-color: black");
    }
    public void updateMiniScore(){
        scoreLabel.setText( " Score : " + screenManager.getScore());
    }

    public void updateShipPosition(){
        miniMapPanel.updateShipPosition();
    }

    public void updateEnemyPositions(){
        miniMapPanel.updateEnemyPositions();
    }

    public void updateMiniWave() {
        waveLabel.setText("Wave : " + screenManager.getWave());
    }

    public void updateMiniLives() {
        livesLabel.setText("Lives : " + screenManager.getShipLives());
    }
}
