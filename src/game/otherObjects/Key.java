package game.otherObjects;

import city.cs.engine.*;
import game.characters.Player;

/**
 * Object that can be collected by the player. It destroys itself on contact with player.
 * Increases the player's collected key amount.
 */
public class Key extends StaticBody {

    public Key(World w, Player player) {
        super(w);
        Shape shape = new BoxShape(1, 1);
        Sensor key = new Sensor(this, shape);
        BodyImage image = new BodyImage("resources/key.png", 1);
        addImage(image);
        key.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent sensorEvent) {
                if (sensorEvent.getContactBody() instanceof Player){
                    player.addKey();
                    System.out.println(player.getKeys());
                    sensorEvent.getSensor().getBody().destroy();
                }
            }

            @Override
            public void endContact(SensorEvent sensorEvent) {

            }
        });
    }
}
