package game.controls;

import game.characters.Player;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key handler to control a Walker.
 */
public class Controller extends KeyAdapter {

    private static final float JUMPING_SPEED = 5;
    private static final float WALKING_SPEED = 5;
    private Player player;

public Controller(Player player) {
    this.player = player;
}

/**
 * Handle key press events for walking.
 * @param e description of the key event
 */
@Override
public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) { // W = walk up
        Vec2 v = player.getLinearVelocity();
        player.jump(JUMPING_SPEED);
    } else if (code == KeyEvent.VK_A) {
        player.startWalking(-WALKING_SPEED); // A = walk left
    } else if (code == KeyEvent.VK_D) {
        player.startWalking(WALKING_SPEED); // D = walk right
    } else if (code == KeyEvent.VK_S) {  //S = walk down
        player.jump(-JUMPING_SPEED);
    } else if (code == KeyEvent.VK_E) {  //S = walk down
        player.switchGuns();
    }
}

/**
 * Handle key release events (stop walking).
 * @param e description of the key event
 */
@Override
public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_A) {
        player.stopWalking();
        player.setLinearVelocity(new Vec2(0,0));
    } else if (code == KeyEvent.VK_D) {
        player.stopWalking();
        player.setLinearVelocity(new Vec2(0,0));
    } else if (code == KeyEvent.VK_S) {
        player.setLinearVelocity(new Vec2(0,0));
    } else if (code == KeyEvent.VK_W) {
        player.setLinearVelocity(new Vec2(0,0));
    }
}

    public void setPlayer(Player player) {
        this.player = player;
    }


}
