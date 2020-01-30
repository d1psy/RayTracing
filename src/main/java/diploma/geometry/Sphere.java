package diploma.geometry;

import diploma.Creator;
import diploma.ray.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sphere extends GeometryObject {

    public Sphere(int id) {
        super(id);
    }

    @Override
    public List<Intersection> getIntersection(Ray ray) {
        Ray ray2 = ray.transform(getTransformation().inverse());
        double a;
        double b;
        Tuple sphereToRay;
        if (!getTransformation().equals(Creator.createIdentityMatrix())) {
            sphereToRay = ray2.getOrigin().subtract(getPosition());
            a = ray2.getDirection().dotProduct(ray2.getDirection(), "#.#####");
            b = 2 * ray2.getDirection().dotProduct(sphereToRay, "#.#####");
        } else {
            sphereToRay = ray.getOrigin().subtract(getPosition());
            a = ray.getDirection().dotProduct(ray.getDirection(), "#.#####");
            b = 2 * ray.getDirection().dotProduct(sphereToRay, "#.#####");
        }
        double c = sphereToRay.dotProduct(sphereToRay, "#.#####") - 1;
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return Collections.emptyList();
        } else {
            List<Intersection> intersections = new ArrayList<>();
            double first = ((-b - Math.sqrt(discriminant)) / (2 * a));
            double second = ((-b + Math.sqrt(discriminant)) / (2 * a));
            Intersection firstIntersection = new Intersection(first, this);
            Intersection secondIntersection = new Intersection(second, this);
            intersections.add(firstIntersection);
            intersections.add(secondIntersection);
            return intersections;
        }

    }

    @Override
    public Tuple localNormalAt(Tuple localPoint) {
        return localPoint.subtract(getPosition());
    }

}
