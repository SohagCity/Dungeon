package game.listeners;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import game.otherObjects.Projectiles;
import game.otherObjects.Rock;
import game.characters.Spawner;
import game.characters.*;
import game.levels.GameLevel;

/**
 * Checks if player's bullets hit any enemy.
 * Applies damage to the hit enemy.
 * Bullet gets destroyed after impact.
 */

public class Kills implements CollisionListener {

    private Player player;
    //a constructor for each type of mod

    public Kills(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        //checks which monster was hit and subs the damage to the health of the corresponding enemy
        if (e.getOtherBody() instanceof Monster) {
            ((Monster)e.getOtherBody()).gotHit(player.getDmg());
            e.getReportingBody().destroy();
            System.out.println("Tier 1 monster got hit");
        }
        if (e.getOtherBody() instanceof GiantMonster) {
            ((GiantMonster)e.getOtherBody()).gotHit(player.getDmg());
            e.getReportingBody().destroy();
            System.out.println("Giant monster got hit");
        }
        if (e.getOtherBody() instanceof MonsterTier2) {
            ((MonsterTier2)e.getOtherBody()).gotHit(player.getDmg());
            e.getReportingBody().destroy();
            System.out.println("Tier 2 monster got hit");
        }
        if (e.getOtherBody() instanceof Turret) {
            ((Turret)e.getOtherBody()).gotHit(player.getDmg());
            e.getReportingBody().destroy();
            System.out.println("Turret got hit");
        }
        if (e.getOtherBody() instanceof StaticBody){
            e.getReportingBody().destroy();
            System.out.println("A bullet has been destroyed");
        }
        if (e.getOtherBody() instanceof MiniTurret){
            e.getReportingBody().destroy();
            System.out.println("Mini Turret got hit");
        }
        if (e.getOtherBody() instanceof Rock){
            e.getReportingBody().destroy();
            System.out.println("A bullet has been destroyed");
        }
        if (e.getOtherBody() instanceof Projectiles){
            e.getOtherBody().destroy();
            e.getReportingBody().destroy();
            System.out.println("2 projectiles collided and got destroyed");
        }
        if (e.getOtherBody() instanceof Spawner){
            ((Spawner)e.getOtherBody()).gotHit(player.getDmg());
            e.getReportingBody().destroy();
            System.out.println("Spawner got hit");
        }
        if (e.getOtherBody() instanceof Boss) {
            e.getReportingBody().destroy();
            if(((GameLevel)e.getReportingBody().getWorld()).isDamage()) {
                ((Boss) e.getOtherBody()).gotHit(player.getDmg());
                System.out.println("Boss got hit");
            }
        }
        if (e.getOtherBody() instanceof RedMonster) {
            ((RedMonster)e.getOtherBody()).gotHit(player.getDmg());
            e.getReportingBody().destroy();
            System.out.println("Red monster got hit");
        }
        if (e.getOtherBody() instanceof Alien) {
            ((Alien)e.getOtherBody()).gotHit(player.getDmg());
            ((Alien)e.getOtherBody()).setRun();
            e.getReportingBody().destroy();
            System.out.println("Alien got hit");
        }

    }
}