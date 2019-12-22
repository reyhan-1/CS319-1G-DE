package GameLogic;

import java.util.ArrayList;

public class MapManager {
    private CollisionManager collisionManager;
    private Ship ship;
    private ArrayList<Ship> shipsList; // for practical reasons
    private ArrayList<Bullet> bulletsListE, bulletsListS; // separate lists for ship and enemy bullets
    private ArrayList<Enemy> enemiesList;
    private int waveNo;
    private int shipLives;
    private int bombNo;
    private int score;
    private static final int FINAL_WAVE_NO = 3;
    private int[] mountains;

    public MapManager(){
        bulletsListE = new ArrayList<Bullet>();
        bulletsListS = new ArrayList<Bullet>();
        enemiesList = new ArrayList<Enemy>();
        shipsList = new ArrayList<Ship>();
        collisionManager = new CollisionManager();
        waveNo = 1;
        bombNo = 3;
        shipLives = 3;
        score = 0;
        mountains = generateRandomMountains(3200);
    }

    private int[] generateRandomMountains(int width) {
        int[] mountains = new int[width];

        int curr = 20;
        int state = 2;

        int[] targets = new int[]{100, 80, 150, 40, 70, 120, 50, 90, 170, 60, 110, 185, 105};
        int[] spaces = new int[]{50, 30, 100};

        int currentTarget = 0;
        int consecutiveSpaces = 0;

        for (int i = 0; i < width; i++){
            if (state == 0){
                curr++;
                mountains[i] = curr;
            }
            else if (state == 1){
                curr--;
                mountains[i] = curr;
            }
            else{
                mountains[i] = curr;
                consecutiveSpaces--;
            }

            if (currentTarget == curr && state == 0){
                state = 1;
            }
            else if (curr == 20){
                curr++;
                consecutiveSpaces = spaces[(int)(Math.random() * 3)];
                state = 2;
            }
            else if (consecutiveSpaces == 0){
                state = 0;
                currentTarget = targets[(int)(Math.random() * 13)];
                consecutiveSpaces = -1;
            }
        }

        return mountains;
    }

    public void nextWave(){
        waveNo++;
    }

    public int getShipLives(){
        return shipLives;
    }
    public int getBombNo(){
        return bombNo;
    }

    public void decreaseLives(){
        shipLives--;
    }

    public void decreaseBombNo(){
        bombNo--;
    }

    public int getScore(){
        return score;
    }

    public void addScore( int s){
        score = score + s;
    }

    public void createWave(){
        for ( int i = 0; i < waveNo * 3; i++) {
            if(i <= 4) {
                addEnemy(0, (int) (Math.random() * 2800), (int) (Math.random() * 500));
            }
            else if (i <= 7){
                addEnemy(1, (int) (Math.random() * 2800), (int) (Math.random() * 500));
            }
            else{
                addEnemy(0, (int) (Math.random() * 2800), (int) (Math.random() * 500));
                addEnemy(1, (int) (Math.random() * 2800), (int) (Math.random() * 500));
            }
        }
    }

    public int getWaveNo(){
        return waveNo;
    }

    public boolean checkGameOver(){
        if ( shipLives == 0){
            return true;
        }
        else if ( waveNo > FINAL_WAVE_NO){
            return true;
        }
        return false;
    }

    public Ship addShip(int id, int x, int y) {
        ship = new Ship( id, x, y);
        shipsList.add( ship);
        return ship;
    }

    public Enemy addEnemy(int id, int x, int y) {
        Enemy enemy = null;
        if(id == 0){
            enemy = new Lander( id, x, y);
        }
        else if(id == 1){
            enemy = new Baiter(id, x, y, this.getShip());
        }
        enemiesList.add( enemy);
        return enemy;
    }

    public Bullet addBullet( int id, int x, int y, int dir, boolean owner){
        Bullet bullet = new Bullet( id, x, y, dir, owner);
        if ( owner){
            bulletsListS.add( bullet);
        }
        else{
            bulletsListE.add( bullet);
        }
        return bullet;
    }

    public ArrayList<Ship> getShipsList() {
        return shipsList;
    }

    public ArrayList<Enemy> getEnemiesList() {
        return enemiesList;
    }

    public Ship getShip() {
        return ship;
    }

    public ArrayList<Bullet> getBulletsListS() {
        return bulletsListS;
    }

    public ArrayList<Bullet> getBulletsListE() {
        return bulletsListE;
    }

    public int[] getMountains() {
        return mountains;
    }

    public ArrayList<GameCharacter> checkCollisionB_E(ArrayList<Bullet> list1,
                                                      ArrayList<Enemy> list2){
        return collisionManager.getDestroyedB_E( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionB_S(ArrayList<Bullet> list1,
                                                      ArrayList<Ship> list2){
        return collisionManager.getDestroyedB_S( list1, list2);
    }
    public ArrayList<GameCharacter> checkCollisionS_E(ArrayList<Ship> list1,
                                                      ArrayList<Enemy> list2){
        return collisionManager.getDestroyedS_E( list1, list2);
    }
}