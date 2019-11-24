package game.otherObjects;

import city.cs.engine.*;
import game.characters.Player;

/**
 * Static body that when collides with player, player get damaged.
 */
public class Falls extends StaticBody {


    public Falls(World w) {
        super(w);

        Shape middleShape = new BoxShape(0.4f,0.4f);
        Sensor middle = new Sensor(this,middleShape);
        middle.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent sensorEvent) {
                if (sensorEvent.getContactBody() instanceof Player){
                    ((Player) sensorEvent.getContactBody()).gotHit(100);
                }
            }

            @Override
            public void endContact(SensorEvent sensorEvent) {

            }
        });
        BodyImage image = new BodyImage("resources/mine.png",0.8f);
        addImage(image);
    }
}