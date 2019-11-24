package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.gameFunctionalities.Game;
import game.listeners.DoorListener;
import game.otherObjects.*;
import org.jbox2d.common.Vec2;

/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel {


    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

    loadImage("resources/base.png");

        //monsters
        addMonster(new Vec2(-10.5f, 6.5f));

        addGiantMonster(new Vec2 (11,-1.5f));

        addHeart(new Vec2(2,-11.5f));


//        walls
        Shape wallShape= new BoxShape(0.5f,1);
        Gate wall1 = new Gate(this,wallShape,1);
        wall1.setPosition(new Vec2(-9, 10.5f));

        Gate wall3 = new Gate(this,wallShape,3);
        wall3.setPosition(new Vec2(-9, 6.5f));

        Shape wallShape2= new BoxShape(1,0.5f);
        Gate wall2 = new Gate(this,wallShape2,2);
        wall2.setPosition(new Vec2(-2.5f, -10));

        Shape wallShape3= new BoxShape(2,0.5f);
        Gate wall31 = new Gate(this,wallShape3,3);
        wall31.setPosition(new Vec2(10.5f, -2.5f));

        Shape wallShape4= new BoxShape(1.5f,0.5f);
        Gate wall21 = new Gate(this,wallShape4,2);
        wall21.setPosition(new Vec2(2, -10));

        Shape fallShape4= new BoxShape(0.5f,0.5f);
        Gate fall5 = new Gate(this,fallShape4,4);
        fall5.setPosition(new Vec2(2, -2));
        Gate fall6 = new Gate(this,fallShape4,5);
        fall6.setPosition(new Vec2(3, -2));

        //rocks
        addRock(new Vec2(-5.5f, 0.5f));

        addRock(new Vec2(-2.5f, -5.5f));

        addRock(new Vec2(2, 6.5f));

        addRock(new Vec2(3, 6.5f));

        //plate
        Plate plate = new Plate(this,1);
        plate.setPosition(new Vec2(-11.5f, 2.5f));

        Plate plate1 = new Plate(this,2);
        plate1.setPosition(new Vec2(-3.5f, 6.5f));


        Plate plate2 = new Plate(this,3);
        plate2.setPosition(new Vec2(11.5f, -10.5f));

        Plate plate3 = new Plate(this,4);
        plate3.setPosition(new Vec2(0.5f, -0.5f));

        Plate plate4 = new Plate(this,5);
        plate4.setPosition(new Vec2(5.5f, -0.5f));

        //keys
        Key key1 = new Key(this,getPlayer());
        key1.setPosition(new Vec2(2.5f, -3.5f));

        Key key2 = new Key(this,getPlayer());
        key2.setPosition(new Vec2(-10.5f, 10.5f));

        Key key3 = new Key(this,getPlayer());
        key3.setPosition(new Vec2(11, -0.5f));

        //portal
        Portal portal = new Portal(this,  new Vec2(-9,-9));
        portal.setPosition(new Vec2(-10.5f, 6.5f));



        //levels
        Door door = new Door(this,1);
        door.setPosition(new Vec2(12.5f, 4));
        door.addCollisionListener(new DoorListener(game,door));

        Door door2 = new Door(this,6);
        door2.setPosition(new Vec2(-12, -7));
        door2.addCollisionListener(new DoorListener(game,door2));



    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(10, 4);
    }


    @Override
    public boolean isCompleted() {
        return true;
    }
}
