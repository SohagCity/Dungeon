
package game.characters;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The player, the main character.
 */
public class Player extends BaseWalker implements StepListener{


    private static final BodyImage idleRifle = new BodyImage("resources/player.rifle.idle.gif", 4.2f);
    private static final BodyImage idlePistol = new BodyImage("resources/player.pistol.idle.gif", 3.5f);
    private static final BodyImage idleShotgun = new BodyImage("resources/player.shotgun.idle.gif", 4.2f);

    private static final BodyImage shootRifle  = new BodyImage("resources/player.rifle.shoot.gif", 4.2f);
    private static final BodyImage shootPistol = new BodyImage("resources/player.pistol.shoot.gif", 3.5f);
    private static final BodyImage shootShotgun = new BodyImage("resources/player.shotgun.shoot.gif", 4.2f);

    /**
     * Current Image of idle.
     */
    private BodyImage currentIdle =idleRifle;
    /**
     * Current Image of shooting.
     */
    private BodyImage currentGun=shootRifle;
    /**
     * Number representing gun in use.
     */
    private int switchGun=1;
    /**
     * Size of the projectile.
     */
    private float radius = 0.24f;


    /** Number of keys collected. */
    private int keys;
    /**
     * Number of defeated enemies.
     */
    private int kills;

    /**
     * Sound.
     */
    private static SoundClip hurt;

    static {
        try {
            hurt = new SoundClip("resources/classic_hurt.wav");
            System.out.println("Loading hurt sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Initialize a new player.
     * @param world The world
     */
    public Player(World world) {
        super(world, idleRifle);

        setDmg(15);
        //this is the real health
        //setHealth(400);

        //this is to make it easier
        setHealth(800);

        player();
    }

    
    /**
     * The amount of keys collected.
     * @return amount of keys.
     */
    public int getKeys() { return keys; }

    /**
     * Adds a key to the collected key amount.
     */
    public void addKey() {
        keys ++;
    }

    /**
     * Returns the amount of enemies killed.
     * @return number of kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * Adds a kill to the kill counter.
     */
    public void addKill() {
        kills++;
    }

    /**
     * Sets the kill counter.
     * @param kills Number of kills.
     */
    public void setKills(int kills) {
        this.kills = kills;
    }

    /**
     * Increases health by an amount, without exceding the health limit.
     * @param heath Amount of heath.
     */
    public void addHealth(int heath) {
        //real health
//        if(getHealth()+heath>=400){
//            setHealth(400);
//        }else{
//            setHealth(getHealth()+heath);
//        }


        //easier
        if(getHealth()+heath>=800){
            setHealth(800);
        }else{
            setHealth(getHealth()+heath);
        }
    }

    /**
     * Checks if player is still alive.
     * @param stepEvent
     */
    @Override
    public void preStep(StepEvent stepEvent) {
       //checks if player is alive
        if (getHealth()<=0){
            destroy();
            System.out.println("Game Over");
            getWorld().stop();

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


    /**
     * Gets the radius of the projectile the player shoots.
     * @return Size of projectile.
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Gets the current idle image of the player.
     * @return Image.
     */
    public BodyImage getCurretIdle() {
        return currentIdle;
    }

    /**
     * Gets the current shooting image of the player.
     * @return Image.
     */
    public BodyImage getCurrentGun() {
        return currentGun;
    }

    /**
     * Gets which gun the player is using.
     * @return Number representing the gun.
     */
    public int getSwitchGun() {
        return switchGun;
    }

    /**
     * Sets which gun the player is using.
     * @param switchGun Gun to use.
     */
    public void setSwitchGun(int switchGun) {
        this.switchGun = switchGun;
    }

    /**
     * Switches the which gun the player is using. Changes images, damage and size of the projectile.
     */
    public void switchGuns(){
        switchGun++;
        if(switchGun==1){
            currentGun=shootRifle;
            currentIdle=idleRifle;
            removeAllImages();
            addImage(currentIdle);
            setDmg(15);
            radius=0.24f;
        }
        else if(switchGun==2){
            currentGun=shootPistol;
            currentIdle=idlePistol;
            removeAllImages();
            addImage(currentIdle);
            setDmg(25);
            radius=0.3f;
        }
        else if(switchGun==3){
            currentGun=shootShotgun;
            currentIdle=idleShotgun;
            removeAllImages();
            addImage(currentIdle);
            setDmg(10);
            radius=0.5f;
        } else{
            switchGun=0;
            switchGuns();
        }
    }
}

