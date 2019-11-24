package game.characters;

import city.cs.engine.*;
import game.listeners.PlayerGotHit;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Giant Monster character.
 */
public class GiantMonster extends BaseWalker implements StepListener {

    private Player player;
    private float speed=0.1f;
    private static BodyImage image = new BodyImage("resources/giantMonster.gif", 5);
    private static SoundClip hurt;

    static {
        try {
            hurt = new SoundClip("resources/BigMonsterHit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public GiantMonster(World world, Player player) {
        super(world, image);
        giantMonster();
        this.setDmg(200);
        this.setHealth(120);
        this.player = player;
        this.addCollisionListener(new PlayerGotHit(player,getDmg()));
    }

    /**
     * Allows this enemy to follow the player movements.
     * @param stepEvent Description of the step event.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //checks if monster should still be alive
        if (getHealth()<=0) {
            player.addKill();
            destroy();
            System.out.println("Monster Killed");
        }

        Vec2 distance=new Vec2(getPosition().sub(player.getPosition()));

        //decrease and increase speed of the character so that the speed stays constant.
        if(distance.length()<10){
            speed=0.2f;
        }
        if (distance.length()<5){
            speed=0.4f;
        }

        setLinearVelocity(new Vec2(distance.mul(-speed)));

        super.rotate(player.getPosition());
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