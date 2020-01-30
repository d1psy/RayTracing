package diploma.ray;

import diploma.Creator;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ray {

    private Tuple origin;
    private Tuple direction;

    public Ray(Tuple origin, Tuple direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray transform(Matrix matrix) {
        Tuple origin = matrix.multiplyByTuple(getOrigin());
        Tuple direction = matrix.multiplyByTuple(getDirection());
        return new Ray(origin, direction);
    }

    public Tuple position(double time) {
        return getOrigin().add(getDirection().multiply(time));
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
