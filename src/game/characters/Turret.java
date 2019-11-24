package game.characters;

import city.cs.engine.*;
import game.listeners.FriendlyFire;
import game.levels.GameLevel;
import game.listeners.PlayerGotHit;
import game.otherObjects.Projectiles;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * Turret Character.
 */
public class Turret extends BaseWalker implements StepListener {

    private static final BodyImage image =
            new BodyImage("resources/turret.png", 4.2f);

    private Player player;
    private int counter=0;

    private static final Shape attackShape= new CircleShape(0.5f);
    private static final BodyImage attackImage=new BodyImage("resources/cannon.png",1.5f);

    private static SoundClip hurt;
    static {
        try {
            hurt = new SoundClip("resources/TurretHit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Turret(GameLevel world, Player player) {
        super(world, image);
        setDmg(25);
        setHealth(200);
        this.player = player;
    }

    /**
     * Rotates the turret on the direction of the player and shoots every few seconds.
     * @param stepEvent
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //checks if turret is alive
        if (getHealth()<=0){
            player.addKill();
            destroy();
            System.out.println("Monster Killed");
        }
        setLinearVelocity(new Vec2(0,0));

        super.rotate(player.getPosition());

        //shoots every 120 frames
        counter++;
        if (counter%120==0) {
            super.shoot(player,attackShape,attackImage);
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