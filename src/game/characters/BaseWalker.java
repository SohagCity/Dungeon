
package game.characters;

import city.cs.engine.*;
import game.listeners.FriendlyFire;
import game.listeners.PlayerGotHit;
import game.otherObjects.Projectiles;
import org.jbox2d.common.Vec2;

import java.util.logging.SocketHandler;


/**
 * Base class for all characters in the game.
 */

//this class is the base of all characters, they have an image, health and damage
public class BaseWalker extends Walker {

    /**
     * Health of the character
     */
    private int health= 100;
    /**
     * The damage this character does to otehr characters.
     */
    private int dmg= 100;
    /**
     * Shape of the character. This can be changed with other methods in this class.
     */
    private SolidFixture fixture;

    /**
     * Initialise a new character.
     * @param world The world.
     * @param image The image of the character.
     */
    public BaseWalker(World world, BodyImage image) {
        super(world);
        addImage(image);
        fixture= new SolidFixture(this, new BoxShape(0.9f,0.9f));
    }

    public void setHealth(int health){
        this.health=health;
    }
    public int getHealth(){
        return health;
    }
    public int getDmg(){
        return dmg;
    }
    public void setDmg(int dmg){
        this.dmg=dmg;
    }
    public void gotHit(int hit){
        this.health=health-hit;
    }

    /**
     * Changes the base shape of the character to the giantMonster shape.
     */
    public void giantMonster(){
        fixture.destroy();
        SolidFixture fixture= new SolidFixture(this, new BoxShape(1.3f,1.3f));
    }
    /**
     * Changes the base shape of the character to the mini Turret shape.
     */
    public void miniTurret(){
        fixture.destroy();
        SolidFixture fixture= new SolidFixture(this,new BoxShape(1,0.5f));
    }
    /**
     * Changes the base shape of the character to the player shape.
     */
    public void player(){
        fixture.destroy();
        SolidFixture fixture= new SolidFixture(this,new PolygonShape(-0.46f,1.46f, -1.09f,0.87f, -0.74f,-0.15f, 0.15f,-0.4f, 0.6f,0.64f, 0.18f,1.27f));
    }
    /**
     * Changes the base shape of the character to the boss shape.
     */
    public void boss(){
        fixture.destroy();
        SolidFixture fixture= new SolidFixture(this,new PolygonShape(    -3.47f,3.51f, 2.28f,3.62f, 2.93f,0.94f, 0.96f,-0.1f, -2.28f,-0.16f, -4.54f,1.2f));
    }

    /**
     * Rotates the body towards a point.
     * @param pointOfRotation The point of rotation.
     */
    public void rotate(Vec2 pointOfRotation){
        Vec2 distance=new Vec2(this.getPosition().sub(pointOfRotation));
        Vec2 playerPos=new Vec2(pointOfRotation);
        Vec2 enemyPos= this.getPosition();

        //using trigonometry, found the by how many degrees the character has to rotate.
        if((enemyPos.y<playerPos.y) && (enemyPos.x<playerPos.x)) {
            float angle= (float)(Math.toDegrees(Math.atan(distance.y/distance.x)));
            this.setAngleDegrees(90+angle);
        } else if((enemyPos.y>playerPos.y) && (enemyPos.x<playerPos.x)) {
            float angle= (float)(Math.toDegrees(Math.atan(distance.x/distance.y)));
            this.setAngleDegrees(-angle);
        } else if((enemyPos.y>playerPos.y) && (enemyPos.x>playerPos.x)) {
            float angle= (float)(Math.toDegrees(Math.atan(distance.y/distance.x)));
            this.setAngleDegrees(270+angle);
        } else if((enemyPos.y<playerPos.y) && (enemyPos.x>playerPos.x)) {
            float angle= (float)(Math.toDegrees(Math.atan(distance.y/distance.x)));
            this.setAngleDegrees(-90+angle);
        }
    }

    /**
     * Shoots a projectile towards the player.
     * @param player The player.
     * @param attackShape The shape of the projectile.
     * @param image The image of the projectile.
     */
    public void shoot(Player player, Shape attackShape, BodyImage image){
        Vec2 distance=new Vec2(this.getPosition().sub(player.getPosition()));
        Projectiles attack = new Projectiles(getWorld(), attackShape);
        float force = -100;
        attack.applyForce(distance.mul(force));

        //calculates the offset of the projectile, so it puts the bullet right in front of the character.
        float gapX = 0;
        float gapY = 0;
        if (distance.y > 0) {
            gapY = -1.6f;
        }
        if (distance.y < 0) {
            gapY = 1.6f;
        }
        if (distance.x > 0) {
            gapX = -1.6f;
        }
        if (distance.x < 0) {
            gapX = 1.6f;
        }

        attack.setPosition(new Vec2(getPosition().x + gapX, getPosition().y + gapY));
        attack.addCollisionListener(new FriendlyFire());
        attack.addCollisionListener(new PlayerGotHit(player, getDmg()));
        attack.addImage(image);
    }
}