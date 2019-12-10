package GUI;

import GameLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class GamePanel extends Pane {
    private ScreenManager screenManager;
    private boolean theme; // true is night, false is day
    private BackgroundImage background, backgroundDay;

    public GamePanel(ScreenManager sm) throws InterruptedException {
        screenManager = sm;

        // set Background
        theme = true;
        Image backgroundImageNight = new Image("GUI/resources/night_theme.jpg", 3200,
                600, true, true);
        background = new BackgroundImage( backgroundImageNight, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        Image backgroundImageDay = new Image("GUI/resources/day_theme.jpg", 3200,
                600, true, true);
        backgroundDay = new BackgroundImage( backgroundImageDay, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        this.setMinSize(800,600);
        this.setBackground(new Background(background));

        // set Ship
        screenManager.addShip( 0, 300, 300);
        Image shipImage = screenManager.getShip().getSprite();
        ImageView shipImageView = new ImageView(shipImage);
        shipImageView.setFitHeight(100);
        shipImageView.setFitWidth(100);
        shipImageView.setLayoutX( screenManager.getShipPosX());
        shipImageView.setLayoutY( screenManager.getShipPosY());
        shipImageView.setScaleX( 1);
        this.getChildren().add(shipImageView);

        AnimationTimer animator = new AnimationTimer()
        {
            @Override
            public void handle(long arg0)
            {
                for ( int i = 0; i < screenManager.getBulletsList().size(); i++){
                    Bullet bullet = screenManager.getBulletsList().get(i);
                    ImageView bulletImageView = bullet.getImageView();
                    GamePanel.this.getChildren().remove( bulletImageView);
                    bullet.move(bullet.getDirection() * 10, 0);
                    bulletImageView.setLayoutX( bullet.getPosX());
                    bulletImageView.setLayoutY( bullet.getPosY());

                    GamePanel.this.getChildren().add(bulletImageView);
                }
            }
        };
        animator.start();

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
                if ( event.getCode() == KeyCode.SPACE){
                    int direction = (int) shipImageView.getScaleX();
                    Bullet bullet;
                    ImageView bulletImageView = new ImageView( new Image( "GUI/resources/bullet2.png"
                            , 10, 3, false, true));
                    if ( direction == 1) {
                        bullet = screenManager.addBullet(0, screenManager.getShipPosX() + 65,
                                screenManager.getShipPosY() + 55, direction);
                    }
                    else{
                        bullet = screenManager.addBullet(0, screenManager.getShipPosX() + 30,
                                screenManager.getShipPosY() + 55, direction);
                        bulletImageView.setScaleX( -1);
                    }
                    bullet.setImageView( bulletImageView);
                }
                if ( event.getCode() == KeyCode.ESCAPE){
                    screenManager.viewPauseMenu();
                }
            }
        });

    }
    public void changeTheme(){
        if (theme == true){
            this.setBackground(new Background(backgroundDay));
            theme = false;
        }
        else {
            this.setBackground(new Background(background));
            theme = true;
        }
    }
}
