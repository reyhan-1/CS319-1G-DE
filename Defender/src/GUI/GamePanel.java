package GUI;

import GameLogic.InputManager;
import GameLogic.MapManager;
import GameLogic.Ship;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        mapManager.addShip( "new Ship");
        Image shipImage = mapManager.getShip().getSprite();
        ImageView shipImageView = new ImageView(shipImage);
        shipImageView.setFitHeight(100);
        shipImageView.setFitWidth(100);
        shipImageView.setLayoutX( 400);
        shipImageView.setLayoutY( 300);
        this.getChildren().add(shipImageView);

    }
}
