package game.controls;

import game.characters.Player;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Wheel lister to detect wheel movement.
 */
public class WheelListener implements MouseWheelListener {
    private Player player;

    public WheelListener(Player player) {
        this.player=player;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getScrollAmount()>2){
            player.switchGuns();
            System.out.println(e.getScrollAmount());
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
