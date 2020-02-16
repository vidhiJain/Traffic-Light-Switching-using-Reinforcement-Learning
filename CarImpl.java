import interfaces.Car;
import interfaces.RoadMap;
import interfaces.TrafficLight;
import utils.Coords;
import utils.Velocity;

import java.util.List;


public class CarImpl implements Car {
    private Coords position;
    private Velocity velocity;
    private Velocity direction; // desired velocity (if no light)

    public CarImpl(Coords position, Velocity startingVelocity) {
        this.position = position;
        this.velocity = startingVelocity;
        this.direction = new Velocity(
            startingVelocity.getXSpeed(), startingVelocity.getYSpeed());
    }

    public void move(TrafficLight l, RoadMap m) {
        boolean greenLight = 
                l.getDelay() == 0 &&
                l.horizontalGreen() == (direction.getYSpeed() == 0) &&
                m.roomToCrossIntersection(position, direction, l);
        boolean stop =
                !greenLight &&
                m.nextNonCarSquareIsTrafficLight(position,direction,l);
        velocity.setXSpeed(stop ? 0 : direction.getXSpeed());
        velocity.setYSpeed(stop ? 0 : direction.getYSpeed());
        
        position.setX(position.getX() + velocity.getXSpeed());
        position.setY(position.getY() + velocity.getYSpeed());
    }

    public Coords getCoords() {
         return position;
    }

    public Velocity getDirection() {
        return direction;
    }

    public Velocity getVelocity() {
        return velocity;
    }
}
