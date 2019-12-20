package GUI;

import GameLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GamePanel extends Pane {
    private ScreenManager screenManager;
    private boolean theme; // true is night, false is day
    private Image backgroundImageDay, backgroundImageNight;
    private ImageView backgroundIV;
    private AnimationTimer collisionAnimator, enemyAnimator, bulletAnimator;
    private EventHandler<KeyEvent> keyHandler;
    private Ship ship;
    private Rectangle[] dots;
    private SoundManager bulletMusic;
    private SoundManager collisionWithEnemy;
    private int[] mountains;


    public GamePanel(ScreenManager sm) throws InterruptedException {
        screenManager = sm;
        theme = true; // adjust default theme to night
        // arrange default background

        backgroundImageNight = new Image("GUI/resources/darkbg.png", 3200,
                500, true, true);
        backgroundImageDay = new Image("GUI/resources/lightbg.png", 3200,
                500, true, true);

        // imagePortion stands for the displayed 800*600 rectangle on the screen
        backgroundIV = new ImageView( backgroundImageNight);
        Rectangle2D imagePortion = new Rectangle2D(0, 0, 800, 500);
        // using setViewport, we can change the displayed rectangle
        backgroundIV.setViewport( imagePortion);
        this.getChildren().add( backgroundIV);

        // add the ship
        ship = screenManager.addShip( 0, 200, 250);
        // adjust ship's graphical attributes
        ship.getImageView().setLayoutX( screenManager.getShip().getPosX());
        ship.getImageView().setLayoutY( screenManager.getShip().getPosY());
        ship.getImageView().setScaleX( 1);
        // add ship to the pane
        this.getChildren().add(ship.getImageView());

        // add enemies
        screenManager.createWave();
        drawWave();



        dots = new Rectangle[800];
        mountains = sm.getMountains();
        drawDots( 0);

        // animations for bullet movement and collision checking
        enemyAnimator = new AnimationTimer() {
            private long time;
            @Override
            public void handle(long now) {
                // this part is experimental
                // moves enemies up and down at different speeds
                if ( now - time > 10_000_000){
                    for ( int i = 0; i < screenManager.getEnemiesList().size(); i++)
                    {
                        Enemy enemy = screenManager.getEnemiesList().get( i);
                        /*if ( enemy.getDirection() == 1){
                            if ( enemy.getPosY() + 50 > 500) {
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
                        }*/
                        enemy.followShip( ship);
                        enemy.getImageView().setLayoutX( enemy.getPosX() - backgroundIV.getViewport().getMinX());
                        enemy.getImageView().setLayoutY( enemy.getPosY());

                    }
                    time = now;
                    screenManager.updateMiniEnemyCoords();
                    screenManager.updateMiniShipCoords();
                }
            }
        };

        bulletAnimator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for ( int i = 0; i < screenManager.getBulletsListS().size(); i++){
                    // for each bullet in bullets list
                    Bullet bullet = screenManager.getBulletsListS().get(i);
                    // move the bullet behind the scenes
                    bullet.move(bullet.getDirection() * 10, 0);
                    bullet.getImageView().setLayoutX( bullet.getPosX()- backgroundIV.getViewport().getMinX());
                    bullet.getImageView().setLayoutY( bullet.getPosY());
                    // if the bullet is beyond the map, remove it from the list and the GamePanel
                    if ( bullet.getPosX() > backgroundIV.getViewport().getMaxX() ||
                            bullet.getPosX() < backgroundIV.getViewport().getMinX()){
                        screenManager.getBulletsListS().remove( bullet);
                        GamePanel.this.getChildren().remove( bullet.getImageView());
                    }
                }
            }
        };

        collisionAnimator = new AnimationTimer()
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
                    // only half of these iterations will mean an enemy is destroyed,
                    // other half is for destroyed bullets.
                    if ( i % 2 == 0) {
                        screenManager.addScore(10);
                        screenManager.updateMiniScore();
                    }
                }
                for ( int i = 0; i < toDestroy2.size(); i++){
                    toDestroyList.add( toDestroy2.get( i));
                }

                for ( int i = 0; i < toDestroy3.size(); i++){
                    toDestroyList.add( toDestroy3.get( i));
                    collisionWithEnemy = new SoundManager("/Defender/src/GUI/resources/Collision.wav");
                    collisionWithEnemy.playSound();

                    if ( i % 2 == 0) { // has to be called only once for each collision
                        screenManager.decreaseLives();
                        screenManager.updateMiniLives();
                        if (screenManager.getShipLives() == 0) {
                            screenManager.viewGameOver(screenManager.getScore());
                        } else {
                            ship = screenManager.addShip(0, 200, 250);
                            ship.getImageView().setLayoutX( screenManager.getShip().getPosX());
                            ship.getImageView().setLayoutY( screenManager.getShip().getPosY());
                            ship.getImageView().setScaleX( 1);
                            // add ship to the pane
                            GamePanel.this.getChildren().add(ship.getImageView());
                            Rectangle2D restartedMap = new Rectangle2D(0, 0, 800, 500);
                            // using setViewport, we can change the displayed rectangle
                            backgroundIV.setViewport( restartedMap);
                            drawDots( 0);
                        }
                    }
                }

                for ( int i = 0; i < toDestroyList.size(); i++){
                    GameCharacter gc = toDestroyList.get(i);
                    ImageView gcImageView = gc.getImageView();
                    GamePanel.this.getChildren().remove( gcImageView);
                }

                if ( screenManager.getEnemiesList().size() == 0) {
                    if ( screenManager.getWave() < 3) {
                        screenManager.nextWave();
                        screenManager.createWave();
                        drawWave();
                        screenManager.updateMiniWave();
                    }
                    else{
                        screenManager.viewGameOver(screenManager.getScore());
                    }

                }
            }
        };

        startAnimations();

        // input management
        keyHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                // when the ship moves to the right in the gameEngine,
                // the displayed map image moves as well.
                // the ship is always able to move,
                // we only change what the user can see.
                if ( event.getCode() == KeyCode.RIGHT){
                    ship.getImageView().setScaleX( 1); // rotates image to the right
                    if ( ship.getPosX() < 3100) {
                        // gameEngine
                        screenManager.getShip().move(20, 0);
                        // anything below is gui-related
                        if ( ship.getPosX() > 400) {
                            if (ship.getPosX() < 2800) {
                                // if the ship moves right when between 400 and 2800
                                // -400 below is to display the ship in center
                                Rectangle2D activeMap = new Rectangle2D(ship.getPosX() - 400, 0,
                                        800, 500);
                                backgroundIV.setViewport( activeMap);
                                drawDots( (int) backgroundIV.getViewport().getMinX());
                            }
                            else {
                                // if the ship moves right when between 2800 and 3200
                                Rectangle2D activeMap = new Rectangle2D(2400, 0, 800, 500);
                                backgroundIV.setViewport(activeMap);
                                // iv.getViewport().getMinX() is used frequently
                                // it's value is the starting x of the displayed map portion
                                // when the ship is at the final 800*600 area, this value becomes 2400
                                // subtracting this value from ship's actual position gives the
                                // ship's relative position on the screen
                                ship.getImageView().setLayoutX(screenManager.getShip().getPosX() - backgroundIV.getViewport().getMinX());
                                drawDots( (int) backgroundIV.getViewport().getMinX());
                            }
                        }
                        else{
                            // if the ship moves right when between 0 and 400
                            ship.getImageView().setLayoutX(screenManager.getShip().getPosX());
                        }
                    }
                }
                // the below code is explained in its counterpart, KeyCode.RIGHT
                if ( event.getCode() == KeyCode.LEFT){
                    ship.getImageView().setScaleX( -1); // rotates image to the left
                    if ( ship.getPosX() > 0) {
                        screenManager.getShip().move(-20, 0);
                        if ( ship.getPosX() < 2800) {
                            if (ship.getPosX() > 400) {
                                Rectangle2D activeMap = new Rectangle2D(ship.getPosX() - 400, 0, 800,
                                        500);
                                backgroundIV.setViewport( activeMap);
                                drawDots( (int) backgroundIV.getViewport().getMinX());
                            }
                            else {
                                Rectangle2D activeMap = new Rectangle2D(0, 0, 800, 500);
                                backgroundIV.setViewport( activeMap);
                                ship.getImageView().setLayoutX(screenManager.getShip().getPosX());
                                drawDots( (int) backgroundIV.getViewport().getMinX());
                            }
                        }
                        else {
                            ship.getImageView().setLayoutX(screenManager.getShip().getPosX() - backgroundIV.getViewport().getMinX());
                        }
                    }
                }
                if ( event.getCode() == KeyCode.UP){
                    if ( ship.getPosY() > 10) {
                        screenManager.getShip().move(0, -10);
                        ship.getImageView().setLayoutY(screenManager.getShip().getPosY());
                    }
                }
                if ( event.getCode() == KeyCode.DOWN){
                    if ( ship.getPosY() < 470) {
                        screenManager.getShip().move(0, 10);
                        ship.getImageView().setLayoutY(screenManager.getShip().getPosY());
                    }
                }
                if ( event.getCode() == KeyCode.SPACE){
                    bulletMusic = new SoundManager("/Defender/src/GUI/resources/GunSilencer.wav");
                    bulletMusic.playSound();
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
        };
    }


    public void addHandler(){
        screenManager.getMainScene().addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
    }

    public void changeTheme(){
        if (theme == true){
            backgroundIV.setImage( backgroundImageDay);
            theme = false;
        }
        else {
            backgroundIV.setImage( backgroundImageNight);
            theme = true;
        }
    }

    public void removeKeyHandler(){
        screenManager.getMainScene().removeEventHandler( KeyEvent.KEY_PRESSED, keyHandler);
    }

    public void drawWave(){
        for ( int i = 0; i < screenManager.getEnemiesList().size(); i++){
            Enemy enemy = screenManager.getEnemiesList().get( i);
            enemy.getImageView().setLayoutX( enemy.getPosX());
            enemy.getImageView().setLayoutY( enemy.getPosY());
            this.getChildren().add(enemy.getImageView());
        }
    }

    public void stopAnimations(){
        enemyAnimator.stop();
        bulletAnimator.stop();
        collisionAnimator.stop();
    }

    public void startAnimations(){
        enemyAnimator.start();
        bulletAnimator.start();
        collisionAnimator.start();
    }

    public boolean isTheme() {
        return theme;
    }

    public void drawDots( int minX){
        for ( int i = 0; i < 800; i++){
            this.getChildren().remove( dots[i]);
        }
        dots = new Rectangle[800];
        for (int i = 0; i < 800; i++){
            dots[i] = new Rectangle( i, 400 - mountains[i + minX], 1, 1);
            dots[i].setStroke(Color.rgb(255, 153, 51));
            this.getChildren().add(dots[i]);
        }
    }
}
