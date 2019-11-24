package game.listeners;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import game.otherObjects.Projectiles;
import game.characters.Spawner;
import game.characters.*;

/**
 * Destroys projectiles when hitting an enemy or hitting another projectile.
 * Used with enemy projectiles so they can not hit each other.
 */
public class FriendlyFire implements CollisionListener {
    public FriendlyFire(){
    }
    @Override
    public void collide(CollisionEvent e) {
        //checks which monster was hit and subs the damage to the health of the corresponding mob
        if (e.getOtherBody() instanceof MonsterTier2){
            e.getReportingBody().destroy();
            System.out.println("Friendly fire, bullet destroyed");
        }
        if (e.getOtherBody() instanceof Monster){
            e.getReportingBody().destroy();
            System.out.println("Friendly fire, bullet destroyed");
        }
        if (e.getOtherBody() instanceof Turret){
            e.getReportingBody().destroy();
            System.out.println("Friendly fire, bullet destroyed");
        }
        if (e.getOtherBody() instanceof GiantMonster){
            e.getReportingBody().destroy();
            System.out.println("Friendly fire, bullet destroyed");
        }
        if (e.getOtherBody() instanceof RedMonster){
            e.getReportingBody().destroy();
            System.out.println("Projectile Destroyed");
        }
        if (e.getOtherBody() instanceof Spawner){
            e.getReportingBody().destroy();
            System.out.println("Projectile Destroyed");
        }
        if (e.getOtherBody() instanceof StaticBody){
            e.getReportingBody().destroy();
            System.out.println("Projectile Destroyed");
        }
        if (e.getOtherBody() instanceof Alien){
            e.getReportingBody().destroy();
            System.out.println("Projectile Destroyed");
        }
        if (e.getOtherBody() instanceof Projectiles){
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
            System.out.println("2 projectiles collided and got destroyed");
        }
    }
}
