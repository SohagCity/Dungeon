package game.listeners;

import city.cs.engine.*;
import game.otherObjects.Gate;
import game.otherObjects.Plate;
import game.otherObjects.Rock;

import java.util.ArrayList;
import java.util.List;

/**
 * When a rock hits a plate the gate with the corresponding
 * identifier will be destroyed.
 */
public class PlateSensor implements SensorListener {
    private List<StaticBody> body;
    private Plate plate;


    public PlateSensor(Plate plate, List<StaticBody> body ) {
        this.body=new ArrayList<>(body);
        this.plate=plate;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        int x=plate.getPlateNo();
        for (int i = 0; i < body.size(); i++) {
            if (body.get(i) instanceof Gate && sensorEvent.getContactBody() instanceof Rock) {
                if (((Gate) body.get(i)).getGateNo()==x){
                    body.get(i).destroy();
                }
            }
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
