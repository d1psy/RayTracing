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

    public List<Intersection> getIntersection(GeometryObject sphere) {
        Ray ray2 = transform(sphere.getTransformation().inverse());
        double a;
        double b;
        Tuple sphereToRay;
        if (!sphere.getTransformation().equals(Creator.createIdentityMatrix())) {
            sphereToRay = ray2.getOrigin().subtract(sphere.getPosition());
            a = ray2.getDirection().dotProduct(ray2.getDirection(), "#.#####");
            b = 2 * ray2.getDirection().dotProduct(sphereToRay, "#.#####");
        } else {
            sphereToRay = getOrigin().subtract(sphere.getPosition());
            a = getDirection().dotProduct(getDirection(), "#.#####");
            b = 2 * getDirection().dotProduct(sphereToRay, "#.#####");
        }
        double c = sphereToRay.dotProduct(sphereToRay, "#.#####") - 1;
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return Collections.emptyList();
        } else {
            List<Intersection> intersections = new ArrayList<>();
            double first = ((-b - Math.sqrt(discriminant)) / (2 * a));
            double second = ((-b + Math.sqrt(discriminant)) / (2 * a));
            Intersection firstIntersection = new Intersection(first, sphere);
            Intersection secondIntersection = new Intersection(second, sphere);
            intersections.add(firstIntersection);
            intersections.add(secondIntersection);
            return intersections;
        }
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
