package game.otherObjects;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.listeners.RockSensor;


/**
 * A rock that can be moved by the player.
 * It can only move left, right, up and down.
 * Not diagonally.
 * It stops when it hits a wall.
 * The rock will move based on which side the player touched.
 */
public class Rock extends Walker {
    private Sensor up;
    private Sensor down;
    private Sensor left;
    private Sensor right;

    public Rock(World w) {
        super(w);
        Shape upShape = new PolygonShape(2.5f*0.3f,2.5f*0.4f,2.5f* -0.3f,2.5f*0.4f,2.5f* -0.2f,2.5f*0.2f, 2.5f*0.2f,2.5f*0.2f);
        up = new Sensor(this, upShape);
        up.addSensorListener(new RockSensor(this,"down"));

        Shape leftShape = new PolygonShape(2.5f*-0.4f,2.5f*0.3f,2.5f* -0.4f,2.5f*-0.3f,2.5f* -0.2f,2.5f*-0.2f,2.5f* -0.2f,2.5f*0.2f);
        left = new Sensor(this,leftShape);
        left.addSensorListener(new RockSensor(this,"right"));


        Shape rightShape = new PolygonShape(2.5f*0.4f,2.5f*0.3f, 2.5f*0.4f,2.5f*-0.3f, 2.5f*0.2f,2.5f*-0.2f, 2.5f*0.2f,2.5f*0.2f);
        right = new Sensor(this,rightShape);
        right.addSensorListener(new RockSensor(this,"left"));


        Shape downShape = new PolygonShape(2.5f*-0.3f,2.5f*-0.4f, 2.5f*0.3f,2.5f*-0.4f,2.5f* 0.2f,2.5f*-0.2f, 2.5f*-0.2f,2.5f*-0.2f);
        down = new Sensor(this,downShape);
        down.addSensorListener(new RockSensor(this,"up"));

        Shape middleShape = new BoxShape(0.9f,0.9f);
        SolidFixture middle = new SolidFixture(this,middleShape);

        BodyImage image = new BodyImage("resources/rock.png",2);
        addImage(image);

    }
}