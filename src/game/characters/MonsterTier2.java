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
 * Monster tier 2 Character.
 */
public class MonsterTier2 extends BaseWalker  implements StepListener {

    private static BodyImage image = new BodyImage("resources/monsterTier2.png", 3);
    private Player player;
    private int speed=2;
    private Shape attackShape;
    private int counter=0;
    private Projectiles attack;
    private static SoundClip hurt;

    static {
        try {
            hurt = new SoundClip("resources/Monster2Hit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public MonsterTier2(World world, Player player) {
        super(world, image);
        this.player = player;
        setDmg(50);
        attackShape = new CircleShape(0.3f);
    }

    /**
     * Monster tier 2 move set and attacks.
     * It can move horizontally, based on the player position.
     * It shoots projectiles at regual intervals.
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

        //moves the monster right or left depending on player position
        if ((getPosition().x > player.getPosition().x)) {
            setLinearVelocity(new Vec2(0, 0));
            startWalking(-speed);
        } else if ((getPosition().x < player.getPosition().x)) {
            setLinearVelocity(new Vec2(0, 0));
            startWalking(speed);
        }

        //every 60 frames the monster will shoot
        counter++;
        if (counter%60==0) {
            attack = new Projectiles(getWorld(),attackShape);
            attack.addImage(new BodyImage("resources/attack.png",2));
            attack.setPosition(new Vec2(getPosition().x,getPosition().y+1.5f));
            attack.addCollisionListener(new FriendlyFire());
            attack.addCollisionListener(new PlayerGotHit(player, getDmg()));
            attack.applyForce(new Vec2(0,2).mul(100));
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
