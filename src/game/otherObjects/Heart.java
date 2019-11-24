package game.otherObjects;


import city.cs.engine.*;
import game.characters.Player;

/**
 * Object that when hit by the player, it restores part of the player's health.
 * However, it does not surpass the heath limit.
 */
public class Heart extends StaticBody {
    public Heart(World w) {
        super(w);
        Shape shape = new BoxShape(1,1);
        Sensor plate= new Sensor(this,shape);

        BodyImage image = new BodyImage("resources/heart.png",2);
        addImage(image);

        plate.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent sensorEvent) {
                if(sensorEvent.getContactBody() instanceof Player){
                    ((Player) sensorEvent.getContactBody()).addHealth(100);
                    sensorEvent.getSensor().getBody().destroy();
                }
            }

            @Override
            public void endContact(SensorEvent sensorEvent) {

            }
        });
    }

}
