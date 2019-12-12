package GameLogic;

import java.util.ArrayList;

public class CollisionManager {
    public CollisionManager(){
    }
    // the method will be used to check collisions between ship-enemy, ship-bullet, enemy-bullet
    // it will return destroyed GameCharacters in a list
    public ArrayList<GameCharacter> getDestroyedB_E(ArrayList<Bullet> list1, ArrayList<Enemy> list2){
        ArrayList<GameCharacter> destroyedList = new ArrayList<GameCharacter>();
        for ( int i = 0; i < list1.size(); i ++){
            for ( int j = 0; j < list2.size(); j++){
                GameCharacter gc1 = list1.get(i);
                GameCharacter gc2 = list2.get(j);
                boolean destroyed = checkCollision( gc1, gc2);
                if ( destroyed){
                    list1.remove( gc1);
                    list2.remove( gc2);
                    destroyedList.add( gc1);
                    destroyedList.add( gc2);
                }
            }
        }
        return destroyedList;
    }
    public ArrayList<GameCharacter> getDestroyedB_S(ArrayList<Bullet> list1, ArrayList<Ship> list2){
        ArrayList<GameCharacter> destroyedList = new ArrayList<GameCharacter>();
        for ( int i = 0; i < list1.size(); i ++){
            for ( int j = 0; j < list2.size(); j++){
                GameCharacter gc1 = list1.get(i);
                GameCharacter gc2 = list2.get(j);
                boolean destroyed = checkCollision( gc1, gc2);
                if ( destroyed){
                    list1.remove( gc1);
                    list2.remove( gc2);
                    destroyedList.add( gc1);
                    destroyedList.add( gc2);
                }
            }
        }
        return destroyedList;
    }
    public ArrayList<GameCharacter> getDestroyedS_E(ArrayList<Ship> list1, ArrayList<Enemy> list2){
        ArrayList<GameCharacter> destroyedList = new ArrayList<GameCharacter>();
        for ( int i = 0; i < list1.size(); i ++){
            for ( int j = 0; j < list2.size(); j++){
                GameCharacter gc1 = list1.get(i);
                GameCharacter gc2 = list2.get(j);
                boolean destroyed = checkCollision( gc1, gc2);
                if ( destroyed){
                    list1.remove( gc1);
                    list2.remove( gc2);
                    destroyedList.add( gc1);
                    destroyedList.add( gc2);
                }
            }
        }
        return destroyedList;
    }

    public boolean checkCollision( GameCharacter gc1, GameCharacter gc2){
        // Check area limits of gc1
        int gc1LeftLimit = gc1.getPosX();
        int gc1RightLimit = gc1.getPosX() + gc1.getWidth();
        int gc1UpperLimit = gc1.getPosY();
        int gc1LowerLimit = gc1.getPosY() + gc1.getHeight();
        // Check area limits of gc2
        int gc2LeftLimit = gc2.getPosX();
        int gc2RightLimit = gc2.getPosX() + gc2.getWidth();
        int gc2UpperLimit = gc2.getPosY();
        int gc2LowerLimit = gc2.getPosY() + gc2.getHeight();
        // check whether gc2 intervenes into gc1's area
        // first, check in vertical direction
        boolean verticalCheck = false;
        boolean horizontalCheck = false;

        if ( gc2LowerLimit >= gc1UpperLimit){
            if ( gc2.getHeight() < gc1.getHeight()) {
                verticalCheck = true;
            }
            else if ( gc2UpperLimit < gc1UpperLimit){
                verticalCheck = true;
            }
            verticalCheck = true;
        }
        if ( gc2UpperLimit <= gc1LowerLimit){
            if ( gc2.getHeight() < gc1.getHeight()) {
                verticalCheck = true;
            }
            else if ( gc2LowerLimit > gc1LowerLimit){
                verticalCheck = true;
            }
        }
        if ( gc2.getHeight() < gc1.getHeight())
        if ( gc2UpperLimit <= gc1LowerLimit && gc2LowerLimit > gc1LowerLimit){
            System.out.println( "verticalcheck3 true");
            verticalCheck = true;
        }

        if ( gc2RightLimit >= gc1LeftLimit && gc2LeftLimit < gc1LeftLimit){
            System.out.println( "horizontalcheck1 true");
            horizontalCheck = true;
        }
        if ( gc2LeftLimit <= gc1RightLimit && gc2RightLimit > gc1RightLimit){
            System.out.println( "horizontalcheck2 true");
            horizontalCheck = true;
        }

        if ( horizontalCheck && verticalCheck){
            return true;
        }
        else
            return false;
    }

}
