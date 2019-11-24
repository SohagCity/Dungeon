package game.otherObjects;

import city.cs.engine.*;
import game.levels.GameLevel;
import game.listeners.PortalSensor;
import org.jbox2d.common.Vec2;


/**
 * A sensor that moves the player to another location.
 */
public class Portal extends StaticBody {
    /**
     * Initialize a new portal.
     * @param w The World.
     * @param pos The position of where the portal will lead.
     */
    public Portal(World w, Vec2 pos) {
        super(w);
        Shape shape = new BoxShape(1, 1);
        Sensor plate = new Sensor(this, shape);
        BodyImage image = new BodyImage("resources/portal.png", 2);
        addImage(image);
        plate.addSensorListener(new PortalSensor(((GameLevel)w).getPlayer(),pos));
    }
}
