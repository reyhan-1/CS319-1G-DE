package GUI;

import GameLogic.GameEngine;
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
    private ScreenManager screenManager;

    public GamePanel(ScreenManager sm){
        screenManager = sm;

        // set Background
        Image backgroundImage = new Image("GUI/resources/mapbg2.png", 3200,
                600, true, true);
        BackgroundImage background = new BackgroundImage( backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        this.setMinSize(800,600);
        this.setBackground(new Background(background));

        // set Ship
        screenManager.addShip( "new Ship", 300, 300);
        Image shipImage = screenManager.getShip().getSprite();
        ImageView shipImageView = new ImageView(shipImage);
        shipImageView.setFitHeight(100);
        shipImageView.setFitWidth(100);
        shipImageView.setLayoutX( screenManager.getShipPosX());
        shipImageView.setLayoutY( screenManager.getShipPosY());
        this.getChildren().add(shipImageView);

        // input management
        screenManager.getMainScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ( event.getCode() == KeyCode.RIGHT){
                    shipImageView.setScaleX( 1);
                    screenManager.getShip().move( 10, 0);
                    shipImageView.setLayoutX( screenManager.getShip().getPosX());
                }
                if ( event.getCode() == KeyCode.LEFT){
                    shipImageView.setScaleX( -1);
                    screenManager.getShip().move( -10, 0);
                    shipImageView.setLayoutX( screenManager.getShip().getPosX());
                }
                if ( event.getCode() == KeyCode.UP){
                    screenManager.getShip().move( 0, -10);
                    shipImageView.setLayoutY( screenManager.getShip().getPosY());
                }
                if ( event.getCode() == KeyCode.DOWN){
                    screenManager.getShip().move( 0, 10);
                    shipImageView.setLayoutY( screenManager.getShip().getPosY());
                }
                if ( event.getCode() == KeyCode.ESCAPE){
                    screenManager.viewPauseMenu();
                }
            }
        });
    }
}
