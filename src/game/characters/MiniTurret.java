package game.characters;


import city.cs.engine.*;
import game.listeners.FriendlyFire;
import game.listeners.PlayerGotHit;
import game.otherObjects.Projectiles;
import org.jbox2d.common.Vec2;

/**
 * Mini Turret Character
 */
public class MiniTurret extends BaseWalker  implements StepListener {

    private static BodyImage image = new BodyImage("resources/miniTurret.png", 1);
    private Player player;
    private int speed=2;
    private Shape attackShape;
    private int counter=0;
    private Projectiles attack;

    public MiniTurret(World world, Player player) {
        super(world, image);
        this.player = player;
        setDmg(50);
        attackShape = new CircleShape(0.3f);
        miniTurret();
    }

    /**
     * Allows the character to shoot.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //checks if monster should be alive
        if (getHealth()<=0){
            player.addKill();
            destroy();
            System.out.println("Monster Tier 2 Killed");
        }

        //every 60 frames it will shoot a projectile
        counter++;
        if (counter%360==0) {
            attack = new Projectiles(getWorld(),attackShape);
            attack.addImage(new BodyImage("resources/attack1.png",0.5f));
            attack.setPosition(new Vec2(getPosition().x-1.5f,getPosition().y));
            attack.addCollisionListener(new FriendlyFire());
            attack.addCollisionListener(new PlayerGotHit(player, getDmg()));
            attack.applyForce(new Vec2(-2,0).mul(100));
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
