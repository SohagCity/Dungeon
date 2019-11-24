
package game.controls;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.otherObjects.Projectiles;
import game.characters.Player;
import game.levels.GameLevel;
import game.listeners.Kills;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Mouse Handler to control the player.
 */
public class MouseHandler extends MouseAdapter {
    //ignore any reference to "melee", it is something that I am experimenting with.
    private WorldView view;

//    private BodyImage rifleMelee = new BodyImage("resources/player.rifle.melee.gif", 4.8f);
    private Player player;

    public MouseHandler(WorldView view, GameLevel world) {
        this.view = view;
        this.player = world.getPlayer();
    }

    /**
     * Create a projectile that spawns at the player position and moves towards the position of the click.
     * @param e event object containing the mouse position
     */
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //if left click then shoot
        if (SwingUtilities.isLeftMouseButton(e)) {
            BodyImage ballImage= new BodyImage("resources/bullet.png", 2.5f*player.getRadius());
            Shape ballShape= new CircleShape(player.getRadius());
            Projectiles ball = new Projectiles(view.getWorld(), ballShape);
            //change character image to a new one
            player.removeAllImages();
            player.addImage(player.getCurrentGun());
            Vec2 playerPosition = new Vec2(player.getPosition());
            Vec2 distance = new Vec2(view.viewToWorld(e.getPoint()).sub(player.getPosition()));
            float force = 100;
            ball.applyForce(distance.mul(force));

            //calculating where the offset is needed as bullets need to spawn in front of the player
            float gapX = 0;
            float gapY = 0;
            if (distance.y > 0) {
                gapY =0.5f;
            }
            if (distance.y < 0) {
                gapY =- 0.5f;
            }
            if (distance.x > 0) {
                gapX = 0.5f;
            }
            if (distance.x < 0) {
                gapX = - 0.5f;
            }
            //spawning the ball and adding the collisions with walls and mobs
            ball.setPosition(new Vec2(playerPosition.x + gapX, playerPosition.y + gapY));
            ball.addImage(ballImage);
            ball.addCollisionListener(new Kills(player));
        }

//        if (SwingUtilities.isRightMouseButton(e)) {
//            player.removeAllImages();
//            player.addImage(rifleMelee);
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            //set image to the original one
            player.removeAllImages();
            player.addImage(player.getCurretIdle());
    }

    public void setPlayer(GameLevel world) {
        player = world.getPlayer();
    }

}
