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
import java.util.Random;
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
        // set background to default
        this.setBackground(new Background(background));

        // add the ship
        Ship ship = screenManager.addShip( 0, 300, 300);
        // adjust ship's graphical attributes
        ship.getImageView().setLayoutX( screenManager.getShip().getPosX());
        ship.getImageView().setLayoutY( screenManager.getShip().getPosY());
        ship.getImageView().setScaleX( 1);
        // add ship to the pane
        this.getChildren().add(ship.getImageView());

        // add enemies
        for ( int i = 200; i < 400; i = i + 50){
            Enemy enemy = screenManager.addEnemy( 0, 600, i);
            enemy.getImageView().setLayoutX( enemy.getPosX());
            enemy.getImageView().setLayoutY( enemy.getPosY());
            this.getChildren().add(enemy.getImageView());
        }

        // animation for bullet movement and collision checking
        AnimationTimer enemyAnimator = new AnimationTimer() {
            private long time;
            @Override
            public void handle(long now) {
                // this part is experimental
                // moves enemies up and down at different speeds
                if ( now - time > 10_000_000){
                    for ( int i = 0; i < screenManager.getEnemiesList().size(); i++)
                    {
                        Enemy enemy = screenManager.getEnemiesList().get( i);
                        if ( enemy.getDirection() == 1){
                            if ( enemy.getPosY() + 50 > 600) {
                                enemy.setDirection(0);
                            }
                        }
                        else{
                            if ( enemy.getPosY() - 50 < 0) {
                                enemy.setDirection(1);
                            }
                        }


                        if ( enemy.getDirection() == 1) {
                            enemy.move(0, 2 * (i + 1));
                        }
                        else{
                            enemy.move( 0, (-2)*(i + 1));
                        }

                        enemy.getImageView().setLayoutX( enemy.getPosX());
                        enemy.getImageView().setLayoutY( enemy.getPosY());

                    }
                    time = now;
                }
            }
        };

        AnimationTimer bulletAnimator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for ( int i = 0; i < screenManager.getBulletsListS().size(); i++){
                    // for each bullet in bullets list
                    Bullet bullet = screenManager.getBulletsListS().get(i);
                    // move the bullet behind the scenes
                    bullet.move(bullet.getDirection() * 10, 0);
                    bullet.getImageView().setLayoutX( bullet.getPosX());
                    bullet.getImageView().setLayoutY( bullet.getPosY());
                    // if the bullet is beyond the map, remove it from the list
                    if ( bullet.getPosX() > 800){
                        screenManager.getBulletsListS().remove( bullet);
                    }
                }
            }
        };

        AnimationTimer collisionAnimator = new AnimationTimer()
        {
            @Override
            public void handle(long arg0)
            {
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

        enemyAnimator.start();
        bulletAnimator.start();
        collisionAnimator.start();

        // input management
        screenManager.getMainScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ( event.getCode() == KeyCode.RIGHT){
                    ship.getImageView().setScaleX( 1); // rotates image to the right
                    screenManager.getShip().move( 10, 0);
                    ship.getImageView().setLayoutX( screenManager.getShip().getPosX());
                }
                if ( event.getCode() == KeyCode.LEFT){
                    ship.getImageView().setScaleX( -1); // rotates image to the left
                    screenManager.getShip().move( -10, 0);
                    ship.getImageView().setLayoutX( screenManager.getShip().getPosX());
                }
                if ( event.getCode() == KeyCode.UP){
                    screenManager.getShip().move( 0, -10);
                    ship.getImageView().setLayoutY( screenManager.getShip().getPosY());
                }
                if ( event.getCode() == KeyCode.DOWN){
                    screenManager.getShip().move( 0, 10);
                    ship.getImageView().setLayoutY( screenManager.getShip().getPosY());
                }
                if ( event.getCode() == KeyCode.SPACE){
                    int direction = (int) ship.getImageView().getScaleX(); // get ship direction
                    Bullet bullet;
                    //ImageView bulletImageView = new ImageView( new Image( "GUI/resources/bullet2.png"
                      //      , 10, 3, false, true));
                    // this if-else creates the bullet in ship's direction and with respect to ship's location
                    if ( direction == 1) {
                        bullet = screenManager.addBullet(0, screenManager.getShip().getPosX() + 100,
                                screenManager.getShip().getPosY() + 15, direction, true);
                    }
                    else{
                        bullet = screenManager.addBullet(0, screenManager.getShip().getPosX() + 0,
                                screenManager.getShip().getPosY() + 15, direction, true);
                        bullet.getImageView().setScaleX( -1);
                    }
                    GamePanel.this.getChildren().add(bullet.getImageView());
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
