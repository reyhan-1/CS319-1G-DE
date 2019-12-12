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
        theme = true; // adjust default theme to night
        // arrange default background
        Image backgroundImageNight = new Image("GUI/resources/night_theme.jpg", 3200,
                600, true, true);
        background = new BackgroundImage( backgroundImageNight, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        // arrange day theme background
        Image backgroundImageDay = new Image("GUI/resources/day_theme.jpg", 3200,
                600, true, true);
        backgroundDay = new BackgroundImage( backgroundImageDay, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        this.setMinSize(800,600);
        // arrange background to default
        this.setBackground(new Background(background));

        // add ship to game engine
        screenManager.addShip( 0, 300, 300);
        // adjust ship's graphical attributes
        Image shipImage = screenManager.getShip().getSprite();
        ImageView shipImageView = new ImageView(shipImage);
        shipImageView.setLayoutX( screenManager.getShipPosX());
        shipImageView.setLayoutY( screenManager.getShipPosY());
        shipImageView.setScaleX( 1);
        screenManager.getShip().setImageView( shipImageView);
        // add ship to the pane
        this.getChildren().add(shipImageView);

        // add enemy to game engine
        Enemy enemy = screenManager.addEnemy( 0, 100, 200);
        // adjust enemy's graphical attributes
        Image enemyImage = screenManager.getEnemiesList().get(0).getSprite();
        ImageView enemyImageView = new ImageView(enemyImage);
        enemyImageView.setLayoutX( screenManager.getEnemiesList().get(0).getPosX());
        enemyImageView.setLayoutY( screenManager.getEnemiesList().get(0).getPosY());
        enemy.setImageView( enemyImageView);
        // add enemy to the pane
        this.getChildren().add(enemy.getImageView());

        // animation for bullet movement and collision checking
        AnimationTimer animator = new AnimationTimer()
        {
            @Override
            public void handle(long arg0)
            {
                for ( int i = 0; i < screenManager.getBulletsListS().size(); i++){
                    // for each bullet in bullets list
                    Bullet bullet = screenManager.getBulletsListS().get(i);
                    ImageView bulletImageView = bullet.getImageView();
                    // delete the image at its current location
                    GamePanel.this.getChildren().remove( bulletImageView);
                    // move the bullet behind the scenes
                    bullet.move(bullet.getDirection() * 10, 0);
                    bulletImageView.setLayoutX( bullet.getPosX());
                    bulletImageView.setLayoutY( bullet.getPosY());
                    // place the bullet back on the panel
                    GamePanel.this.getChildren().add(bulletImageView);
                }
                ArrayList<GameCharacter> toDestroyList = new ArrayList<GameCharacter>();
                // check collision between ship bullets and enemies
                ArrayList<GameCharacter> toDestroy1 = screenManager.checkCollisionB_E( screenManager.getBulletsListS(),
                        screenManager.getEnemiesList());
                // check collision between enemy bullets and ship
                ArrayList<GameCharacter> toDestroy2 = screenManager.checkCollisionB_S( screenManager.getBulletsListE(),
                        screenManager.getShipsList());
                // check collision between ship and enemies
                ArrayList<GameCharacter> toDestroy3 = screenManager.checkCollisionS_E( screenManager.getShipsList(),
                        screenManager.getEnemiesList());
                // add characters that will be destroyed into toDestroyList
                for ( int i = 0; i < toDestroy1.size(); i++){
                    toDestroyList.add( toDestroy1.get( i));
                }
                for ( int i = 0; i < toDestroy2.size(); i++){
                    toDestroyList.add( toDestroy2.get( i));
                }
                for ( int i = 0; i < toDestroy3.size(); i++){
                    toDestroyList.add( toDestroy3.get( i));
                }

                for ( int i = 0; i < toDestroyList.size(); i++){
                    GameCharacter gc = toDestroyList.get(i);
                    ImageView gcImageView = gc.getImageView();
                    GamePanel.this.getChildren().remove( gcImageView);
                }

            }
        };
        animator.start();

        // input management
        screenManager.getMainScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ( event.getCode() == KeyCode.RIGHT){
                    shipImageView.setScaleX( 1); // rotates image to the right
                    screenManager.getShip().move( 10, 0);
                    shipImageView.setLayoutX( screenManager.getShip().getPosX());
                }
                if ( event.getCode() == KeyCode.LEFT){
                    shipImageView.setScaleX( -1); // rotates image to the left
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
                    int direction = (int) shipImageView.getScaleX(); // get ship direction
                    Bullet bullet;
                    ImageView bulletImageView = new ImageView( new Image( "GUI/resources/bullet2.png"
                            , 10, 3, false, true));
                    // this if-else creates the bullet in ship's direction and with respect to ship's location
                    if ( direction == 1) {
                        bullet = screenManager.addBullet(0, screenManager.getShipPosX() + 100,
                                screenManager.getShipPosY() + 15, direction, true);
                    }
                    else{
                        bullet = screenManager.addBullet(0, screenManager.getShipPosX() + 0,
                                screenManager.getShipPosY() + 15, direction, true);
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
