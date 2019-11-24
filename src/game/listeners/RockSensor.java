package game.listeners;

import city.cs.engine.*;
import game.otherObjects.Rock;
import game.characters.Player;
import org.jbox2d.common.Vec2;


/**
 * The sensor that allows the rock to move.
 */
public class RockSensor implements SensorListener {
    private Rock body;
    private String direction;

    public RockSensor(Rock body, String direction){
        this.body=body;
        this.direction=direction;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof Player){
            if (direction.equals("down")) {
                body.setLinearVelocity(new Vec2(0,0));
                body.stopWalking();
                body.jump(-5);
            } else if(direction.equals("right")){
                body.setLinearVelocity(new Vec2(0,0));
                body.stopWalking();
                body.startWalking(5);
            }else if(direction.equals("left")){
                body.setLinearVelocity(new Vec2(0,0));
                body.stopWalking();
                body.startWalking(-5);
            }else if(direction.equals("up")){
                body.setLinearVelocity(new Vec2(0,0));
                body.stopWalking();
                body.jump(5);
            }
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
