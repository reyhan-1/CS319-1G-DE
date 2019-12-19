package GUI;

import GameLogic.Enemy;
import GameLogic.Ship;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniMapPanel extends Pane {
    private ScreenManager screenManager;
    private Line verticalLine1, verticalLine2;
    private ArrayList<Rectangle> dots, miniEnemies;
    private Rectangle miniShip;

    public MiniMapPanel( ScreenManager sm) {

        screenManager = sm;
        verticalLine1 = new Line(0, 0, 0, 95);
        verticalLine1.setStroke(Color.rgb(238, 219, 0));

        verticalLine2 = new Line(400, 0, 400, 95);
        verticalLine2.setStroke(Color.rgb(238, 219, 0));

        dots = new ArrayList<>();
        int[] mountains = screenManager.getMountains();

        for (int i = 0; i < 400; i++) {
            //System.out.println(95 - mountains[8 * i] / 5);
            dots.add( new Rectangle( i,  90 - mountains[8 * i] / 5, 1, 1));
            dots.get(i).setStroke(Color.rgb(255, 153, 51));
            this.getChildren().add(dots.get(i));
        }

        Ship ship = screenManager.getShip();
        miniShip = new Rectangle(ship.getPosX() / 8, ship.getPosY() / 5, 2, 2);
        miniShip.setStroke(Color.WHITE);
        this.getChildren().add( miniShip);

        ArrayList<Enemy> enemiesList = screenManager.getEnemiesList();
        miniEnemies = new ArrayList<>();
        for ( int i = 0; i < enemiesList.size(); i++){
            Enemy enemy = enemiesList.get( i);
            miniEnemies.add( new Rectangle( enemy.getPosX() / 8, enemy.getPosY() / 5, 2, 2));
            miniEnemies.get(i).setStroke(Color.RED);
            this.getChildren().add(miniEnemies.get(i));
        }

        this.getChildren().addAll( verticalLine1, verticalLine2);
    }
    public void updateShipPosition(){
        Ship ship = screenManager.getShip();
        miniShip.setX(ship.getPosX() / 8);
        miniShip.setY(ship.getPosY() / 5);
    }

    public void updateEnemyPositions() {
        ArrayList<Enemy> enemiesList = screenManager.getEnemiesList();
        for ( int i = 0; i < miniEnemies.size(); i++){
            this.getChildren().remove( miniEnemies.get(i));
        }
        miniEnemies.clear();
        for ( int i = 0; i < enemiesList.size(); i++){
            Enemy enemy = enemiesList.get( i);
            miniEnemies.add( new Rectangle( enemy.getPosX() / 8, enemy.getPosY() / 5, 2, 2));
            miniEnemies.get(i).setStroke(Color.RED);
            this.getChildren().add(miniEnemies.get(i));
        }
    }
}
