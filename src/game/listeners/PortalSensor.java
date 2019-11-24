package game.listeners;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.characters.Player;
import org.jbox2d.common.Vec2;

/**
 * A sensor listener that activates when collides with player
 * and he possess 3 keys.
 * When activated it will teleport the player to another position.
 */
public class PortalSensor implements SensorListener {
    private Player player;
    private Vec2 pos;
    public PortalSensor(Player player, Vec2 pos) {
        this.player=player;
        this.pos=pos;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Player && player.getKeys()==3){
            player.setPosition(pos);
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
