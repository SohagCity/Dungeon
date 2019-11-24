package game.levels;

import game.otherObjects.Door;
import game.listeners.DoorListener;
import game.gameFunctionalities.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 3 of the game
 */
public class Level3 extends GameLevel {


    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        loadImage("resources/baseLevel3.png");

        //make monsters
        addTurret(new Vec2(10,10));
        addTurret(new Vec2(10,-10));

        addSpawner(new Vec2(10,3),false);
        addSpawner(new Vec2(10,-3),false);

        Door door = new Door(this,4);
        door.setPosition(new Vec2(12, 0));
        door.addCollisionListener(new DoorListener(game,door));

        Door door2 = new Door(this,1);
        door2.setPosition(new Vec2(-12, 0));
        door2.addCollisionListener(new DoorListener(game,door2));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-9.5f, 0);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
