package entity;

import component.GameComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cuan on 9/8/15.
 */
public class GameEntity {
    private final Integer ID;
    private final List<GameComponent> componentList;

    public GameEntity(int ID){
        this.ID = ID;
        componentList = new ArrayList<>();
    }

    public String toString(){
        return ID.toString();
    }

    public void attachComp(GameComponent comp){
        componentList.add(comp);
    }

    public GameComponent getComp(int compID){
        for(GameComponent gc: componentList){
            if(gc.familyID == compID){
                return gc;
            }
        }

        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("GameEntity->getComp("+compID+") is not in: "+ID);
            e.printStackTrace();
        }
        return null;
    }

    public boolean containsCompID(int compID){
        for(GameComponent gc: componentList){
            if(gc.familyID == compID){
                return true;
            }
        }
        return false;
    }

}