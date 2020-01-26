package diploma.math;

import diploma.Creator;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.ray.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static diploma.math.RayMath.dotProduct;
import static diploma.math.RayMath.subtract;
import static diploma.math.RayMath.transform;

public class IntersectionCollection {

    private List<Intersection> intersections;

    public IntersectionCollection(Intersection... intersections) {
        this.intersections = new ArrayList<>();
        this.intersections.addAll(Arrays.asList(intersections));
    }

    public void addIntersections(Intersection... intersections) {
        this.intersections.addAll(Arrays.asList(intersections));
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public static List<Intersection> getIntersection(Ray ray, GeometryObject sphere) {
        Ray ray2 = transform(ray, MatrixMath.inverse(sphere.getTransformation()));
        float a;
        float b;
        Tuple sphereToRay;
        if (!sphere.getTransformation().equals(Creator.createIdentityMatrix())) {
            sphereToRay = subtract(ray2.getOrigin(), sphere.getPosition());
            a = dotProduct(ray2.getDirection(), ray2.getDirection());
            b = 2 * dotProduct(ray2.getDirection(), sphereToRay);
        } else {
            sphereToRay = subtract(ray.getOrigin(), sphere.getPosition());
            a = dotProduct(ray.getDirection(), ray.getDirection());
            b = 2 * dotProduct(ray.getDirection(), sphereToRay);
        }
        float c = dotProduct(sphereToRay, sphereToRay) - 1;
        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return Collections.emptyList();
        } else {
            List<Intersection> intersections = new ArrayList<>();
            float first = (float) ((-b - Math.sqrt(discriminant)) / (2 * a));
            float second = (float) ((-b + Math.sqrt(discriminant)) / (2 * a));
            Intersection firstIntersection = new Intersection(first, sphere);
            Intersection secondIntersection = new Intersection(second, sphere);
            intersections.add(firstIntersection);
            intersections.add(secondIntersection);
            return intersections;
        }
    }

    public Intersection hit() {
        List<Intersection> hitList = new ArrayList<>(intersections);
        Collections.sort(hitList);
        for(Intersection temp : hitList) {
            if (temp.getTime() >= 0) {
                return temp;
            }
        }
        return null;
    }
}
