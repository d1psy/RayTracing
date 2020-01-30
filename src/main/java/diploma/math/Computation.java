package diploma.math;

import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Tuple;
import diploma.ray.Ray;

import java.util.ArrayList;
import java.util.List;

public class Computation {

    private double time;
    private GeometryObject object;
    private Tuple point;
    private Tuple eyeVector;
    private Tuple normalVector;
    private Tuple overPoint;
    private boolean inside;
    private Tuple reflectVector;
    private double n1;
    private double n2;

    public Computation() {

    }

    public Computation(Intersection intersection, Ray ray, List<Intersection> xs) {
        prepareComputations(intersection, ray, xs);
    }

    public void prepareComputations(Intersection intersection, Ray ray, List<Intersection> xs) {
        time = intersection.getTime();
        object = intersection.getGeometryObject();
        point = ray.position(time);
        eyeVector = ray.getDirection().negate();
        normalVector = object.normalAt(point);
        reflectVector = ray.getDirection().reflect(normalVector);
        if (normalVector.dotProduct(eyeVector, "#.#####") < 0) {
            inside = true;
            normalVector = normalVector.negate();
        } else {
            inside = false;
        }
        overPoint = point.add(normalVector.multiply(0.00001));
        List<GeometryObject> containers = new ArrayList<>();
        for (Intersection inter : xs) {
            if (inter.equals(intersection)) {
                if (containers.isEmpty()) {
                    n1 = 1;
                } else {
                    n1 = containers.get(containers.size() - 1).getMaterial().getRefractiveIndex();
                }
            }
            if (containers.contains(inter.getGeometryObject())) {
                containers.remove(inter.getGeometryObject());
            } else {
                containers.add(inter.getGeometryObject());
            }
            if (inter.equals(intersection)) {
                if (containers.isEmpty()) {
                    n2 = 1;
                } else {
                    n2 = containers.get(containers.size() - 1).getMaterial().getRefractiveIndex();
                }
                break;
            }
        }
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

    public Tuple getReflectVector() {
        return reflectVector;
    }

    public void setReflectVector(Tuple reflectVector) {
        this.reflectVector = reflectVector;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }
}
