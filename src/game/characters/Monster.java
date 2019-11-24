
package game.characters;

import city.cs.engine.*;
import game.levels.GameLevel;
import game.listeners.PlayerGotHit;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Monster Character.
 */
public class Monster extends BaseWalker implements StepListener {

    private Player player;
    private float speed=0.1f;
    private static BodyImage image = new BodyImage("resources/monster.png", 4);
    private static SoundClip hurt;

    static {
        try {
            hurt = new SoundClip("resources/MonsterHit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Monster(GameLevel world, Player player) {
        super(world, image);
        setDmg(50);
        setHealth(70);
        this.player = player;
        this.addCollisionListener(new PlayerGotHit(player,getDmg()));
    }

    /**
     * Allows this enemy to follow the player movements.
     * @param stepEvent Description of the step event.
     */
    @Override
    public void preStep(StepEvent stepEvent) {

        Vec2 distance=new Vec2(getPosition().sub(player.getPosition()));

        //checks if monster should still be alive
        if (getHealth()<=0) {
            player.addKill();
            destroy();
            System.out.println("Monster Killed");
        }
        //decrease and increase speed to keep it constant.
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