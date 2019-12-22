package GUI;


import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;




public class MiniGamePanel extends Pane {
    private ScreenManager screenManager;
    private Label scoreLabel, waveLabel, livesLabel;
    private Line line;
    private MiniMapPanel miniMapPanel;
    private Image sbomb, sbomb2, sbomb3;
    private ImageView ba, ba2, ba3;

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

        // bomb image
        sbomb = new Image("GUI/resources/bomb3.png", 200,
                    500, true, true);

        ba = new ImageView(sbomb);
        Rectangle2D imagePortion = new Rectangle2D(0, 0, 3000, 500);
        // using setViewport, we can change the displayed rectangle
        ba.setViewport(imagePortion);
        this.getChildren().add(ba);

        line = new Line( 0, 95, 800, 100);
        line.setStroke( Color.rgb(238, 219, 0));


        miniMapPanel = new MiniMapPanel( screenManager);

        infoGrid.add( scoreLabel, 2, 0);
        infoGrid.add( livesLabel, 0, 1);
        infoGrid.add( miniMapPanel, 1,0);
        infoGrid.add( waveLabel, 2, 1);
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
    public void updateBombImage() {
        if(screenManager.getBombNo() == 2) {
            this.getChildren().remove(ba);
            sbomb = new Image("GUI/resources/bomb2.png", 200,
                    500, true, true);
            ba = new ImageView(sbomb);
            Rectangle2D imagePortion = new Rectangle2D(0, 0, 3000, 500);
            // using setViewport, we can change the displayed rectangle
            ba.setViewport(imagePortion);
            this.getChildren().add(ba);
        }
        if(screenManager.getBombNo() == 1) {
            this.getChildren().remove(ba);
            sbomb = new Image("GUI/resources/bomb1.png", 200,
                    500, true, true);
            ba = new ImageView(sbomb);
            Rectangle2D imagePortion = new Rectangle2D(0, 0, 3000, 500);
            // using setViewport, we can change the displayed rectangle
            ba.setViewport(imagePortion);
            this.getChildren().add(ba);
        }
        if(screenManager.getBombNo() == 0) {
            this.getChildren().remove(ba);
        }
    }
}
