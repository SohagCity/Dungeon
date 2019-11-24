package game.characters;

import city.cs.engine.*;
import game.characters.BaseWalker;
import game.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


/**
 * The spawner character.
 */
public class Spawner extends BaseWalker implements StepListener {

    private static BodyImage image = new BodyImage("resources/spawner.png", 2);
    private int count=0;
    private int count2=1;
    private boolean atBoss=false;
    private static SoundClip hurt;
    private static BodyImage imageBoss = new BodyImage("resources/spawnerBoss.png", 2);

    static {
        try {
            hurt = new SoundClip("resources/TurretHit.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Spawner(World world) {
        super(world, image);
        this.setDmg(0);
        this.setHealth(65);

    }

    /**
     * Every few seconds it will create a new Monster enemy.
     * Also if at the boss, every 2 monster enemies it will spawn a red monster.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        //checks if monster should still be alive
        if (getHealth()<=0){
            destroy();
        }

        setLinearVelocity(new Vec2(0,0));

        //creates new monsters every 200 frames.
        count++;
        if (count%200==0){
            if(count2%4==0 && atBoss){
                ((GameLevel) getWorld()).addRedMonster(new Vec2(getPosition().x - 1, getPosition().y));
            }else {
                ((GameLevel) getWorld()).addMonster(new Vec2(getPosition().x - 1, getPosition().y));
            }
            count2++;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    /**
     * When at the boss the spawner has increased health,
     * its image changes and red monsters are allowed to spawn.
     *
     */
    public void spawnerBoss(){
        atBoss=true;
        removeAllImages();
        addImage(imageBoss);
        setHealth(10000000);
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

    public boolean isAtBoss() {
        return atBoss;
    }
}
