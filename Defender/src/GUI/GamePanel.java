package GUI;

import GameLogic.MapManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class GamePanel extends Pane {
    private MapManager mapManager;
    private ScreenManager screenManager;

    public GamePanel(ScreenManager screenManager1){
        screenManager = screenManager1;
        mapManager = screenManager.getGameEngine().getMapManager();
        Image backgroundImage = new Image("GUI/resources/mapbg2.png", 3200,
                600, true, true);
        BackgroundImage background = new BackgroundImage( backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        this.setMinSize(800,600);
        this.setBackground(new Background(background));

    }
}
