package game.levels;

import game.otherObjects.Door;
import game.listeners.DoorListener;
import game.gameFunctionalities.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 4 of the game
 */
public class Level4 extends GameLevel {

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        loadImage("resources/baseLevel4.png");

        //make monsters
        for(float y=-11.5f; y<13;y=y+1) {
            if (y > 1.5f || y < -1.4f) {
                addMiniTurret(new Vec2(12, y));
            }
        }

        addHeart(new Vec2(10,0));

        Door door = new Door(this,5);
        door.setPosition(new Vec2(12, 0));
        door.addCollisionListener(new DoorListener(game,door));

        Door door2 = new Door(this,3);
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
