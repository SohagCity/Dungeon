package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.characters.*;
import game.gameFunctionalities.Game;
import game.otherObjects.DamagePhase;
import game.otherObjects.Falls;
import game.otherObjects.Heart;
import game.otherObjects.Rock;
import org.jbox2d.common.Vec2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Player player;
    private Monster monster;
    private MonsterTier2 monsterTier2;
    private Turret turret;
    private GiantMonster giantMonster;
    public Player getPlayer() {
        return player;
    }
    private Boss boss;
    private boolean damage=false;
    /**
     * Populate the world of this level.
     * Child classes should use this method with additional bodies.
     */
    public void populate(Game game) {

        setGravity(0);
        player = new Player(this);
        player.setPosition(startPosition());
        addStepListener(player);
    }
    
    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    
    /** Is this level complete? */
    public abstract boolean isCompleted();

    /**
     * Adds an enemy of type monster to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addMonster(Vec2 pos){
        Monster m=new Monster(this,player);
        addStepListener(m);
        m.setPosition(pos);
    }
    /**
     * Adds an enemy of type red monster to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addRedMonster(Vec2 pos){
        RedMonster m=new RedMonster(this,player);
        addStepListener(m);
        m.setPosition(pos);
    }
    /**
     * Adds an enemy of type monster tier 2 to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addMonsterTier2(Vec2 pos){
        MonsterTier2 m=new MonsterTier2(this,player);
        addStepListener(m);
        m.setPosition(pos);
        m.setAngleDegrees(180);
    }
    /**
     * Adds an enemy of type turret to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addTurret(Vec2 pos){
        Turret m=new Turret(this,player);
        addStepListener(m);
        m.setPosition(pos);
    }
    /**
     * Adds an enemy of type giant monster to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addGiantMonster(Vec2 pos){
        GiantMonster m=new GiantMonster(this,player);
        addStepListener(m);
        m.setPosition(pos);
    }
    /**
     * Adds an enemy of type mini turret to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addMiniTurret(Vec2 pos){
        MiniTurret m=new MiniTurret(this,player);
        addStepListener(m);
        m.setPosition(pos);
    }
    /**
     * Adds an enemy of type spawner to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addSpawner(Vec2 pos, Boolean atBoss){
        Spawner m=new Spawner(this);
        addStepListener(m);
        m.setPosition(pos);
        if (atBoss){
            m.spawnerBoss();
        }
    }
    /**
     * Adds an enemy of type boss to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addBoss(Vec2 pos){
        boss=new Boss(this,player);
        addStepListener(boss);
        boss.setAngleDegrees(90);
        boss.setPosition(pos);
        boss.setHealth(800);
    }
    /**
     * Adds a heart to the world at specified coordinates.
     * @param pos Position of the heart.
     */
    public void addHeart(Vec2 pos){
        Heart m=new Heart(this);
        m.setPosition(pos);
    }
    /**
     * Adds rock to the world at specified coordinates.
     * @param pos Position of the enemy.
     */
    public void addRock(Vec2 pos){
        Rock m=new Rock(this);
        m.setPosition(pos);
    }

    public void addAlien(Vec2 pos){
        Alien m=new Alien(this,player);
        addStepListener(m);
        m.setPosition(pos);
    }

    /**
     * Returns the boss.
     * @return Boss.
     */
    public Boss getBoss(){
        return boss;
    }

    /**
     * Adds a damage phase object to the world at specified coordinates.
     * @param pos Position of the damage phase object.
     */
    public void addDamagePhase(Vec2 pos){
        DamagePhase m=new DamagePhase(this);
        m.setPosition(pos);
    }

    /**
     * Returns if damage phase being active or not.
     * @return Damage.
     */
    public boolean isDamage() {
        return damage;
    }

    /**
     * Sets the damage phase.
     * @param damage Damage active or not.
     */
    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    /**
     * Reads an image and generates objects based on the colours of the pixel at specific positions.
     * If the pixel is black, add a wall.
     * If pixel is blue, add a fall.
     * @param path Path of the image.
     */
    public void loadImage(String path){
        BufferedImage image=null;
        try{
            image= ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("error loading tile map");
        }
        int w=image.getWidth();
        int h=image.getHeight();
        for(int x=0; x<h; x++){
            for(int y=0; y<w; y++){
                int pixel=image.getRGB(x,y);
                int red=(pixel>>16)& 0xff;
                int green= (pixel >>8) & 0xff;
                int blue =(pixel) & 0xff;

                if(red==0 && green==0 && blue==0){
                    Shape wallShape= new BoxShape(0.4f,0.4f);
                    StaticBody wall = new StaticBody(this,wallShape);
                    wall.setPosition(new Vec2(x-13, 13-y));
                    Color c=new Color(255,255,255,1 );
                    wall.setFillColor(c);
                    wall.setLineColor(c);
                }
                if(red==0 && green==0 && blue==255){
                    Falls wall = new Falls(this);
                    wall.setPosition(new Vec2(x-13, 13-y));
                }
            }
        }

    }
}
