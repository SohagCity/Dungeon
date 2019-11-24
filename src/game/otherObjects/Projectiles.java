package game.otherObjects;

import city.cs.engine.*;


/**
 * The projectiles enemies and player will shoot.
 */
public class Projectiles extends DynamicBody{

    public Projectiles(World world, Shape shape) {
        super(world,shape);
        setBullet(true);
    }
}
