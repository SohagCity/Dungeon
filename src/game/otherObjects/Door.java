package game.otherObjects;

import city.cs.engine.*;

import java.awt.*;

/**
 * Doors in a game. When the actor collides with a door, if
 * the current level is complete the game is advanced to the
 * next level. 
 */
public class Door extends StaticBody {   

    private int doorTo;
    /**
     * Initialise a new door.
     * @param world The world.
     */
    public Door(World world, int doorTo) {
        super(world, new BoxShape(0.2f, 1));
        Color c=new Color(255,255,255,1 );
        setFillColor(c);
        setLineColor(c);
        this.doorTo=doorTo;
    }

    /**
     *
     * @return which level the door leads to
     */
    public int getDoorTo() {
        return doorTo;
    }
}
