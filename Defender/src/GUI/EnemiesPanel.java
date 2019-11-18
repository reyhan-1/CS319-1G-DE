package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.concurrent.TimeUnit;

public class EnemiesPanel extends Pane {
    public EnemiesPanel( ScreenManager sm) throws InterruptedException {
        Image enemiesImage = new Image("GUI/resources/bg.png", 800,
                600, true, true);
        ImageView enemiesImageView = new ImageView( enemiesImage);
        enemiesImageView.setFitHeight( 600);
        enemiesImageView.setFitWidth( 800);
        enemiesImageView.setLayoutX( 0);
        enemiesImageView.setLayoutY( 0);
        this.setMinSize( 800, 600);

        Button button = new Button ("Continue on to the game");
        button.setMinSize( 200, 100);
        button.setLayoutX(200);
        button.setLayoutY(300);

        this.getChildren().addAll( enemiesImageView, button);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sm.viewGame();
            }
        });
    }
}
