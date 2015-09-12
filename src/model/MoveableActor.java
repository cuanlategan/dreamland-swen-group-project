package model;

import control.DIR;
import control.MovementStrategy;

import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 */
public abstract class MoveableActor extends AbstractActor {
    public MoveableActor(ID id, Location location, Image image, boolean collidable, boolean drawable) {
        super(id, location, image, collidable, drawable);
    }

    abstract public void move(DIR dir);

    abstract public boolean canMove(DIR dir);

    abstract public void setMoveStrat(MovementStrategy moveStrat);

    abstract public MovementStrategy getMoveStrat();

}