package game.levels;

import city.cs.engine.*;
import game.otherObjects.Door;
import game.listeners.DoorListener;
import game.gameFunctionalities.Game;
import org.jbox2d.common.Vec2;

/**
 * Level 1 of the game
 */
public class Level1 extends GameLevel {

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        loadImage("resources/baseLevel1.png");

        addTurret(new Vec2(-7,7));
        addMonsterTier2(new Vec2(5,-5));
        addMonster(new Vec2(5,5));
        // make the wall
        StaticBody wall = new WorldObjects(this);
        wall=((WorldObjects) wall).walls();
        wall.setPosition(new Vec2(0, 10));

        // make the bridges
        StaticBody bridge = new WorldObjects(this);
        bridge=((WorldObjects) bridge).bridges();
        bridge.setPosition(new Vec2(11, 0));

        StaticBody bridge1 = new WorldObjects(this);
        bridge1=((WorldObjects) bridge1).bridges();
        bridge1.setPosition(new Vec2(-11, 0));

        Door door = new Door(this,3);
        door.setPosition(new Vec2(12, 0));
        door.addCollisionListener(new DoorListener(game,door));

        Door door2 = new Door(this,2);
        door2.setPosition(new Vec2(-12, 0));
        door2.addCollisionListener(new DoorListener(game,door2));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0,0);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getKills()>=3;
    }
}
