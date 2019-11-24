package game.otherObjects;

import city.cs.engine.*;
import game.characters.Player;
import game.levels.GameLevel;


/**
 * body used in boss battle, when collected player can deal damage to the boss;
 */
public class DamagePhase extends StaticBody implements StepListener{
    /**
     * Can damage be inflicted to boss?
     */
    private boolean damage;

    /**
     * Timer.
     */
    private int count=0;

    /**
     * The world.
     */
    private GameLevel world;

    /**
     * Initialize a new damage phase.
     * When picked up it will allow the player to damage the boss.
     * @param world
     */
    public DamagePhase(GameLevel world) {
        super(world);
        this.world=world;
        Shape shape = new BoxShape(1,1);
        Sensor plate= new Sensor(this,shape);
        BodyImage image = new BodyImage("resources/skull.png",2);
        addImage(image);
        plate.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent sensorEvent) {
                if(sensorEvent.getContactBody() instanceof Player) {
                    world.setDamage(true);
                    count=0;
                    sensorEvent.getSensor().getBody().removeAllImages();
                    sensorEvent.getSensor().removeAllSensorListeners();
                    world.addStepListener((DamagePhase)sensorEvent.getSensor().getBody());
                }
            }

            @Override
            public void endContact(SensorEvent sensorEvent) {

            }
        });
    }

    public boolean isDamage() {
        return damage;
    }
    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    /**
     * Starts a timer for few seconds where the player is able to deal damage.
     * @param  stepEvent desciption of the step event.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        if(world.isDamage()){
            count++;
            if(count%300==0){
                world.setDamage(false);
            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
