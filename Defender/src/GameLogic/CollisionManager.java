package GameLogic;

import java.util.ArrayList;

public class CollisionManager {
    public CollisionManager(){
    }
    // the method will be used to check collisions between ship-enemy, ship-bullet, enemy-bullet
    // it will return destroyed GameCharacters in a list
    public ArrayList<GameCharacter> getDestroyedB_E(ArrayList<Bullet> list1, ArrayList<Enemy> list2){
        ArrayList<GameCharacter> destroyedList = new ArrayList<GameCharacter>();
        for ( int i = list1.size() - 1; i >= 0; i--){
            GameCharacter gc1 = list1.get(i);
            for ( int j = list2.size() - 1; j >= 0; j--){
                GameCharacter gc2 = list2.get(j);
                boolean destroyed = checkCollision( gc1, gc2);
                if ( destroyed){
                    // must check if a gc hasn't already been removed from its list
                    if ( list1.contains(gc1)) {
                        list1.remove(gc1);
                        destroyedList.add(gc1);
                    }
                    if ( list2.contains( gc2)) {
                        list2.remove(gc2);
                        destroyedList.add(gc2);
                    }
                }
            }
        }
        return destroyedList;
    }
    public ArrayList<GameCharacter> getDestroyedB_S(ArrayList<Bullet> list1, ArrayList<Ship> list2){
        ArrayList<GameCharacter> destroyedList = new ArrayList<GameCharacter>();
        for ( int i = list1.size() - 1; i >= 0; i--){
            GameCharacter gc1 = list1.get(i);
            for ( int j = list2.size() - 1; j >= 0; j--){
                GameCharacter gc2 = list2.get(j);
                boolean destroyed = checkCollision( gc1, gc2);
                if ( destroyed){
                    // must check if a gc hasn't already been removed from its list
                    if ( list1.contains(gc1)) {
                        list1.remove(gc1);
                        destroyedList.add(gc1);
                    }
                    if ( list2.contains( gc2)) {
                        list2.remove(gc2);
                        destroyedList.add(gc2);
                    }
                }
            }
        }
        return destroyedList;
    }
    public ArrayList<GameCharacter> getDestroyedS_E(ArrayList<Ship> list1, ArrayList<Enemy> list2){
        ArrayList<GameCharacter> destroyedList = new ArrayList<GameCharacter>();
        for ( int i = list1.size() - 1; i >= 0; i--){
            GameCharacter gc1 = list1.get(i);
            for ( int j = list2.size() - 1; j >= 0; j--){
                GameCharacter gc2 = list2.get(j);
                boolean destroyed = checkCollision( gc1, gc2);
                if ( destroyed){
                    // must check if a gc hasn't already been removed from its list
                    if ( list1.contains(gc1)) {
                        list1.remove(gc1);
                        destroyedList.add(gc1);
                    }
                    if ( list2.contains( gc2)) {
                        list2.remove(gc2);
                        destroyedList.add(gc2);
                    }
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
        // first if is vertical check
        if ( gc2UpperLimit < gc1LowerLimit && gc2LowerLimit > gc1UpperLimit){
            // second if is horizontal check
            if ( gc2RightLimit > gc1LeftLimit && gc2LeftLimit < gc1RightLimit){
                return true;
            }
        }
        return false;
    }

}
