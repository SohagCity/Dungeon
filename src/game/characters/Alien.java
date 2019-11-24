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
 * Alien character (FSM).
 */
public class Alien extends BaseWalker implements StepListener {
    /**
     * The player character.
     */
    private Player player;
    /**
     * The speed of which this character is moving.
     */
    private float speed=0;

    /**
     * Image of the character.
     */
    private static BodyImage image = new BodyImage("resources/alien.png", 4);
    /**
     * Sound effect when getting hit.
     */
    private static SoundClip hurt;

    /**
     * Character in running mode.
     */
    private boolean run=false;

    /**
     * Timer.
     */
    private int counter=0;

    /**
     * The projectile this character shoots.
     */
    private Projectiles attack;

    /**
     * The shape of the projectile.
     */
    private static final Shape attackShape = new CircleShape(0.3f);
    /**
     *  Image for the projectile.
     */
    private static final BodyImage attackImage= new BodyImage("resources/cannon.png", 1.5f);

    /**
     * The different states of the character.
     */
    private enum State {
        WALK, RUN, SLEEP
    }
    /**
     * Current state.
     */
    private State state;

    //loading sound
    static {
        try {
            hurt = new SoundClip("resources/MonsterHit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Initialize a new Alien.
     * @param world The world.
     * @param player The Player.
     */
    public Alien(GameLevel world, Player player) {
        super(world, image);
        setDmg(50);
        setHealth(150);
        this.player = player;
        state=State.SLEEP;
        this.addCollisionListener(new PlayerGotHit(player,getDmg()));
    }

    /**
     * Check if player is in range.
      * @return Player in range.
     */
    public boolean inRange() {
        Vec2 distance = new Vec2(getPosition().sub(player.getPosition()));
        return distance.length()<8;
    }

    /**
     * Sets running.
     */
    public void setRun(){
        run=true;
    }

    /**
     * Allows this enemy to follow the player movements if in range.
     * If shot by the player, it will start running (increasing speed) and also start shooting.
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

        //did the enemy get hit?
        if(run) {
            //if not running, run
            if (state != State.RUN) {
                state = State.RUN;
                speed=0.5f;
            }
            //shoot every 30 frames
            counter++;
            if (counter%30==0) {
                super.shoot(player,attackShape,attackImage);
            }
            //if in range start walking
        } else if(inRange()){
                if (state != State.WALK) {
                    state = State.WALK;
                    speed=0.2f;
                }
                //otherwise stop moving
        }else {
            if (state != State.SLEEP) {
                state = State.SLEEP;
                run=false;
                speed=0;
            }
        }
        //walk towards player.
        setLinearVelocity(new Vec2(distance.mul(-speed)));
        //rotate
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
