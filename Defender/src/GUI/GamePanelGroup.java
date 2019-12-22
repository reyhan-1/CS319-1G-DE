package GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GamePanelGroup extends Pane {
    public GamePanelGroup(ScreenManager sm){
        GridPane gridPane = new GridPane();
        gridPane.add( sm.getMiniGamePanel(), 0, 0);
        gridPane.add( sm.getGamePanel(), 0, 1);

        this.setMinSize( 800, 600);
        this.getChildren().add( gridPane);
    }
}