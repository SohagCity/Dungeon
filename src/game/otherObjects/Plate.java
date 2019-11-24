package game.otherObjects;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.listeners.PlateSensor;

/**
 * A sensor that activates when hit by a rock.
 */
public class Plate extends StaticBody {
    private int plateNo;
    public Plate(World w, int plateNo) {
        super(w);
        Shape shape = new BoxShape(1,1);
        Sensor plate= new Sensor(this,shape);
        this.plateNo=plateNo;
        BodyImage image = new BodyImage("resources/plate.png",2);
        addImage(image);
        plate.addSensorListener(new PlateSensor(this,getWorld().getStaticBodies()));
    }

    public int getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(int plateNo) {
        this.plateNo = plateNo;
    }
}
