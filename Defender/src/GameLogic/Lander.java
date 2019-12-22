package GameLogic;

import java.util.Random;

public class Lander extends Enemy{
    private int xTrajectory;
    private int yTrajectory;
    public Lander(int id, int x, int y) {
        super(id, x, y, "GUI/resources/lander.png", 100);
        xTrajectory = -1;
        yTrajectory = -1;
        this.setVeloXMult( 1);
        this.setVeloYMult( 1);
    }

    @Override
    public void move() {
        if(((xTrajectory == this.getPosX()) && (yTrajectory == this.getPosY()))||((xTrajectory == -1) && (yTrajectory == -1))){
            Random randomX = new Random();
            Random randomY = new Random();
            boolean xDir = randomX.nextBoolean();
            boolean yDir = randomX.nextBoolean();
            if(xDir){
                xTrajectory = this.getPosX() + randomX.nextInt(400);
            }
            else{
                xTrajectory = this.getPosX() - randomX.nextInt(400);
            }
            if(yDir){
                yTrajectory = this.getPosY() + randomY.nextInt(400);
            }
            else{
                yTrajectory = this.getPosY() - randomY.nextInt(400);
            }
            if(xTrajectory > 3180){
                xTrajectory = 3180;
            }
            else if(xTrajectory < 20){
                xTrajectory = 20;
            }
            if(yTrajectory > 480){
                yTrajectory = 480;
            }
            else if(yTrajectory < 0){
                yTrajectory = 20;
            }
        }
        else {
            if((this.getPosX() < xTrajectory) && (this.getPosY() < yTrajectory)){
                this.setPosX(this.getPosX() + this.getVeloXMult());
                this.setPosY(this.getPosY() + this.getVeloYMult());
            }
            else if((this.getPosX() < xTrajectory) && (this.getPosY() > yTrajectory)){
                this.setPosX(this.getPosX() + this.getVeloXMult());
                this.setPosY(this.getPosY() - this.getVeloYMult());
            }
            else if((this.getPosX() > xTrajectory) && (this.getPosY() > yTrajectory)){
                this.setPosX(this.getPosX() - this.getVeloXMult());
                this.setPosY(this.getPosY() - this.getVeloYMult());
            }
            else if((this.getPosX() > xTrajectory) && (this.getPosY() < yTrajectory)){
                this.setPosX(this.getPosX() - this.getVeloXMult());
                this.setPosY(this.getPosY() + this.getVeloYMult());
            }
            else{
                xTrajectory = -1;
                yTrajectory = -1;
            }
        }
    }
}
