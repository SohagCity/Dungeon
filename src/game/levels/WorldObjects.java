
package game.levels;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Objects around the world.
 */
public class WorldObjects extends StaticBody{
     
    public WorldObjects(World w) {
        super(w);
    }

    /**
     * A wall.
     * @return The wall.
     */
    public StaticBody walls(){
        Shape wallShape = new BoxShape(11, 1.7f);
        Fixture fixture= new SolidFixture(this,wallShape);
        addImage(new BodyImage("resources/wall1.png", 3.5f));
        return this;
    }

    /**
     * A bridge
     * @return the bridge.
     */
    public StaticBody bridges(){
        Shape bridgeShape = new BoxShape(1.5f, 1);
        GhostlyFixture bridgeFixture = new GhostlyFixture(this, bridgeShape);
        addImage(new BodyImage("resources/bridge.png", 1.5f));
        return this;
    }
}
