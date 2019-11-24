package game.characters;

import city.cs.engine.*;
import game.listeners.FriendlyFire;
import game.listeners.PlayerGotHit;
import game.otherObjects.Projectiles;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Boss character.
 */
public class Boss extends BaseWalker  implements StepListener {

    private static BodyImage image = new BodyImage("resources/boss.png", 9);
    private Player player;
    private int speed=2;
    private Shape attackShape;
    private int counter=0;
    private Projectiles attack;
    private Projectiles attack1;
    private static SoundClip hurt;

    /**
     * Loads sound for this character.
     */
    static {
        try {
            hurt = new SoundClip("resources/BigMonsterHit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Initialise a new boss.
     * @param world The world.
     * @param player The player.
     */
    public Boss(World world, Player player) {
        super(world, image);
        this.player = player;
        setDmg(50);
        attackShape = new CircleShape(0.3f);
        boss();
    }

    /**
     * Boss move set and attacks.
     * It can move vertically, based on the player position.
     * It shoots 2 parallel projectiles.
     * @param stepEvent description of the step event.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //checks if monster should be alive
        if (getHealth()<=0){
            player.addKill();
            destroy();
            System.out.println("Monster Tier 2 Killed");
        }

        //moves the monster up or down depending on player position
        if ((getPosition().y > player.getPosition().y)) {
            setLinearVelocity(new Vec2(0, 0));
            jump(-speed);
        } else if ((getPosition().y < player.getPosition().y)) {
            setLinearVelocity(new Vec2(0, 0));
            jump(speed);
        }


        //every 60 frames the monster will shoot 2 parallel projectiles.
        counter++;
        if (counter%60==0) {
            attack = new Projectiles(getWorld(),attackShape);
            attack.addImage(new BodyImage("resources/attack.png",2));
            attack.setPosition(new Vec2(getPosition().x+1.5f,getPosition().y-2));
            attack.addCollisionListener(new FriendlyFire());
            attack.addCollisionListener(new PlayerGotHit(player, getDmg()));
            attack.applyForce(new Vec2(2,0).mul(100));

            attack1 = new Projectiles(getWorld(),attackShape);
            attack1.addImage(new BodyImage("resources/attack.png",2));
            attack1.setPosition(new Vec2(getPosition().x+1.5f,getPosition().y+2));
            attack1.addCollisionListener(new FriendlyFire());
            attack1.addCollisionListener(new PlayerGotHit(player, getDmg()));
            attack1.applyForce(new Vec2(2,0).mul(100));
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    /**
     * Reduces health of this character by the amount specified.
     * Plays a sound when getting hit.
     * @param hit player's damage
     */
    @Override
    public void gotHit(int hit) {
        hurt.play();
        super.gotHit(hit);
    }
}