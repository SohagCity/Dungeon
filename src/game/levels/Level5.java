package game.levels;

import game.otherObjects.Door;
import game.listeners.DoorListener;
import game.gameFunctionalities.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 1 of the game
 */
public class Level5 extends GameLevel {

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        loadImage("resources/baseLevel5.png");

        addAlien(new Vec2(10,10));
        addAlien(new Vec2(0,9));
        addAlien(new Vec2(-5,-9));
        addAlien(new Vec2(2,2));
        addAlien(new Vec2(8,-4));
        addAlien(new Vec2(0,-7));
        addAlien(new Vec2(-8,8));
        addAlien(new Vec2(-5,5));
        addAlien(new Vec2(10,-7));

        Door door = new Door(this,4);
        door.setPosition(new Vec2(-12, -8));
        door.addCollisionListener(new DoorListener(game,door));

        Door door2 = new Door(this,6);
        door2.setPosition(new Vec2(-12, 8));
        door2.addCollisionListener(new DoorListener(game,door2));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-10,-8);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
