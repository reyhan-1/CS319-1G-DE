package GUI;

import GameLogic.InputManager;
import GameLogic.MapManager;
import GameLogic.Ship;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class GamePanel extends Pane {
    private MapManager mapManager;
    private ScreenManager screenManager;
    private InputManager inputManager;

    public GamePanel(ScreenManager screenManager1){
        screenManager = screenManager1;
        mapManager = screenManager.getGameEngine().getMapManager();
        inputManager = screenManager.getGameEngine().getInputManager();
        Image backgroundImage = new Image("GUI/resources/mapbg2.png", 3200,
                600, true, true);
        BackgroundImage background = new BackgroundImage( backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        this.setMinSize(800,600);
        this.setBackground(new Background(background));

        Ship ship = mapManager.addShip( "new Ship", 300, 300);
        Image shipImage = ship.getSprite();
        ImageView shipImageView = new ImageView(shipImage);
        shipImageView.setFitHeight(100);
        shipImageView.setFitWidth(100);
        shipImageView.setLayoutX( ship.getPosX());
        shipImageView.setLayoutY( ship.getPosY());
        this.getChildren().add(shipImageView);

        screenManager.getMainScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ( event.getCode() == KeyCode.RIGHT){
                    ship.move( 10, 0);
                    shipImageView.setLayoutX( ship.getPosX());
                }
                if ( event.getCode() == KeyCode.LEFT){
                    ship.move( -10, 0);
                    shipImageView.setLayoutX( ship.getPosX());
                }
                if ( event.getCode() == KeyCode.UP){
                    ship.move( 0, -10);
                    shipImageView.setLayoutY( ship.getPosY());
                }
                if ( event.getCode() == KeyCode.DOWN){
                    ship.move( 0, 10);
                    shipImageView.setLayoutY( ship.getPosY());
                }
            }
        });
    }
}
