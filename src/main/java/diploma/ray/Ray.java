package diploma.ray;

import diploma.geometry.Tuple;

public class Ray {

    private Tuple origin;
    private Tuple direction;

    public Ray(Tuple origin, Tuple direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Tuple getOrigin() {
        return origin;
    }

    public Tuple getDirection() {
        return direction;
    }

    public void setOrigin(Tuple origin) {
        this.origin = origin;
    }

    public void setDirection(Tuple direction) {
        this.direction = direction;
    }
}
