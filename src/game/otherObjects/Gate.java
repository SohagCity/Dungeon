package game.otherObjects;

import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

import java.awt.*;

/**
 * Adds a wall to the world with an identifier.
 */
public class Gate extends StaticBody {
    private int gateNo;

    /**
     *
     * @param w The world.
     * @param s The Shape of the wall.
     * @param gateNo The identifier of this wall.
     */
    public Gate(World w, Shape s, int gateNo) {
        super(w, s);
        setFillColor(Color.gray);
        this.gateNo=gateNo;
    }

    public int getGateNo() {
        return gateNo;
    }

    public void setGateNo(int gateNo) {
        this.gateNo = gateNo;
    }
}
