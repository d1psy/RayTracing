package diploma.math;

import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Tuple;
import diploma.ray.Ray;

public class Computation {

    private double time;
    private GeometryObject object;
    private Tuple point;
    private Tuple eyeVector;
    private Tuple normalVector;
    private Tuple overPoint;
    private boolean inside;

    public Computation() {

    }

    public Computation(Intersection intersection, Ray ray) {
        prepareComputations(intersection, ray);
    }

    public void prepareComputations(Intersection intersection, Ray ray) {
        time = intersection.getTime();
        object = intersection.getGeometryObject();
        point = ray.position(time);
        eyeVector = ray.getDirection().negate();
        normalVector = object.normalAt(point);
        if (normalVector.dotProduct(eyeVector, "#.#####") < 0) {
            inside = true;
            normalVector = normalVector.negate();
        } else {
            inside = false;
        }
        overPoint = point.add(normalVector.multiply(0.00001));
    }

    public double getTime() {
        return time;
    }

    public GeometryObject getObject() {
        return object;
    }

    public Tuple getPoint() {
        return point;
    }

    public Tuple getEyeVector() {
        return eyeVector;
    }

    public Tuple getNormalVector() {
        return normalVector;
    }

    public boolean isInside() {
        return inside;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setObject(GeometryObject object) {
        this.object = object;
    }

    public void setPoint(Tuple point) {
        this.point = point;
    }

    public void setEyeVector(Tuple eyeVector) {
        this.eyeVector = eyeVector;
    }

    public void setNormalVector(Tuple normalVector) {
        this.normalVector = normalVector;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public Tuple getOverPoint() {
        return overPoint;
    }

    public void setOverPoint(Tuple overPoint) {
        this.overPoint = overPoint;
    }
}
