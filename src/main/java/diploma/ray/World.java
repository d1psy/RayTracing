package diploma.ray;

import diploma.geometry.Color;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Point;
import diploma.geometry.Tuple;
import diploma.math.Computation;
import diploma.math.IntersectionCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {

    private Light light;
    private List<GeometryObject> geometryObjects;

    public World() {
        light = new Light(new Color(1.0, 1.0, 1.0), new Point(-10, 10, -10));
        geometryObjects = new ArrayList<>();
    }

    public World(Light light) {
        this.light = light;
        geometryObjects = new ArrayList<>();
    }

    public List<Intersection> getWorldIntersections(Ray ray) {
        List<Intersection> temp = new ArrayList<>();
        for (GeometryObject object : getGeometryObjects()) {
            temp.addAll(object.getIntersection(ray));
        }
        Collections.sort(temp);
        return temp;
    }

    public Tuple shadeHit(Computation computation, int remaining) {
        Tuple surface = computation.getObject().getMaterial().lighting(getLight(),
                computation.getPoint(),
                computation.getEyeVector(),
                computation.getNormalVector(),
                isShadowed(computation.getOverPoint()));
        Tuple reflected = reflectedColor(computation, remaining);
        return surface.add(reflected);
    }

    public Tuple colorAt(Ray ray, int remaining) {
        List<Intersection> intersections = getWorldIntersections(ray);
        Intersection intersection = IntersectionCollection.hit(intersections);
        if (intersection == null) {
            return new Color(0, 0, 0);
        }
        Computation computation = new Computation(intersection, ray, intersections);
        return shadeHit(computation, remaining);
    }

    public boolean isShadowed(Tuple point) {
        Tuple v = getLight().getPosition().subtract(point);
        double distance = v.magnitude();
        Tuple direction = v.normalize();
        Ray ray = new Ray(point, direction);
        List<Intersection> objects = getWorldIntersections(ray);
        Intersection intersection = IntersectionCollection.hit(objects);
        return intersection != null && intersection.getTime() < distance;
    }

    public Tuple reflectedColor(Computation computation, int remaining) {
        if (computation.getObject().getMaterial().getReflectivity() == 0 || remaining <= 0) {
            return new Color(0, 0, 0);
        }
        Ray ray = new Ray(computation.getOverPoint(), computation.getReflectVector());
        Tuple color = colorAt(ray, remaining - 1);
        return color.multiply(computation.getObject().getMaterial().getReflectivity());
    }

    public Light getLight() {
        return light;
    }

    public List<GeometryObject> getGeometryObjects() {
        return geometryObjects;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public void addGeometryObject(GeometryObject object) {
        geometryObjects.add(object);
    }
}
