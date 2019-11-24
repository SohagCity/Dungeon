package game.listeners;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.characters.Player;
import game.otherObjects.Projectiles;

//checks if player got hit by a mob

/**
 * If player gets hit then it will decrease his health by the amount specified.
 */
public class PlayerGotHit implements CollisionListener {

    private Player player;
    private int dmg;

    /**
     *  Initialize the collision.
      * @param player The player.
     * @param dmg The amount of damage that has been taken.
     */
    public PlayerGotHit(Player player, int dmg) {
        this.player = player;
        this.dmg = dmg;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == player) {
            //if player gets it then subs the corresponding mob's damage to his health
            player.gotHit(dmg);
            System.out.println("Player got hit");
            System.out.println("Player health: " + player.getHealth());
            //if it was hit by a projectile then it will destroy it
            if (e.getReportingBody() instanceof Projectiles) {
                e.getReportingBody().destroy();
                System.out.println("Enemy projectile has been destroyed due to player being hit");

            }
        }
    }
}
