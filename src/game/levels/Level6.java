package game.levels;

import game.otherObjects.Door;
import game.listeners.DoorListener;
import game.gameFunctionalities.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 4 of the game
 */
public class Level6 extends GameLevel {


    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        loadImage("resources/baseBoss.png");

        //addBoss(new Vec2(-8,11));

        addSpawner(new Vec2(10,10),true);
        addSpawner(new Vec2(10,-10),true);


        Door door = new Door(this,7);
        door.setPosition(new Vec2(-12, 0));
        door.addCollisionListener(new DoorListener(game,door));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(10f, 0);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
