package game.controls;

import city.cs.engine.WorldView;
import game.characters.Player;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Rotates the player based on the mouse position, so that the player is always looking at the mouse position.
 */
public class MouseMovement implements MouseMotionListener {

    private Player player;
    private WorldView view;

    public MouseMovement(Player player, WorldView view){
        this.player = player;
        this.view=view;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vec2 mousePos= new Vec2(view.viewToWorld(e.getPoint()));
        player.rotate(mousePos);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
