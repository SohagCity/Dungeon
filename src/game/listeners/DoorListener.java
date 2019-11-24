package game.listeners;

import city.cs.engine.*;
import game.otherObjects.Door;
import game.gameFunctionalities.Game;
import game.characters.Player;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if the current level is complete the game is advanced to the next level. 
 */
public class DoorListener implements CollisionListener {
    private Game game;
    private Door door;
    public DoorListener(Game game, Door door) {
        this.game = game;
        this.door=door;
    }


    @Override
    public void collide(CollisionEvent e) {
        Player player = game.getPlayer();
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            game.goNextLevel(door.getDoorTo());
        }
    }
}
