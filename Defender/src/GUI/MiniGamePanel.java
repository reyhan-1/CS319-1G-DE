package GUI;

import GameLogic.Enemy;
import GameLogic.Ship;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class MiniGamePanel extends Pane {
    private ScreenManager screenManager;
    private Label scoreLabel, waveLabel, livesLabel;
    private Line line;
    private Line verticalLine1;
    private Line verticalLine2;
    private Rectangle[] dots;
    private Rectangle[] enemies;
    private Rectangle ship;
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
        line = new Line( 0, 95, 800, 100);
        line.setStroke( Color.rgb(238, 219, 0));

        verticalLine1 = new Line( 200, 0, 200, 97);
        verticalLine1.setStroke( Color.rgb(238, 219, 0));

        verticalLine2 = new Line( 600, 0, 600, 97);
        verticalLine2.setStroke( Color.rgb(238, 219, 0));

        dots = new Rectangle[400];
        int[] mountains = sm.getMountains();

        for (int i = 0; i < 400; i++){
            dots[i] = new Rectangle(200+i, 90 - mountains[8 * i] / 6, 1, 1);
            dots[i].setStroke(Color.rgb(255, 153, 51));
            this.getChildren().add(dots[i]);
        }

        Ship s = sm.getShip();
        ship = new Rectangle(200 + s.getPosX() / 8, s.getPosY() / 6, 2, 2);
        ship.setStroke(Color.WHITE);
        this.getChildren().add(ship);

        ArrayList<Enemy> enemiesList = sm.getEnemiesList();
        enemies = new Rectangle[enemiesList.size() * 100];
        int i = 0;
        for (Enemy e : enemiesList){
            enemies[i] = new Rectangle(200 + e.getPosX() / 8, e.getPosY() / 6, 2, 2);
            enemies[i].setStroke(Color.RED);
            this.getChildren().add(enemies[i]);

            i++;
        }

        this.getChildren().addAll( scoreLabel, line, verticalLine1, verticalLine2);
        this.setMinSize( 800, 100);
        this.setStyle("-fx-background-color: black");
    }
    public void updateMiniScore(){
        scoreLabel.setText( " Score : " + screenManager.getScore());
    }

    public void updateShipPosition(){
        Ship s = screenManager.getShip();
        ship.setX(200 + s.getPosX() / 8);
        ship.setY(s.getPosY() / 6);
    }

    public void updateEnemyPositions(){
        ArrayList<Enemy> enemiesList = screenManager.getEnemiesList();

        int i = 0;
        for (Enemy e : enemiesList){
            enemies[i].setX(200 + e.getPosX() / 8);
            enemies[i].setY(e.getPosY() / 6);

            i++;
        }
    }

    public void updateMiniWave() {
        waveLabel.setText("Wave : " + screenManager.getWave());
    }

    public void updateMiniLives() {
        livesLabel.setText("Lives : " + screenManager.getShipLives());
    }
}
