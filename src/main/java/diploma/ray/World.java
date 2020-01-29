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
            temp.addAll(ray.getIntersection(object));
        }
        Collections.sort(temp);
        return temp;
    }

    public Tuple shadeHit(Computation computation) {
        return computation.getObject().getMaterial().lighting(getLight(), computation.getPoint(), computation.getEyeVector(), computation.getNormalVector());
    }

    public Tuple colorAt(Ray ray) {
        List<Intersection> intersections = getWorldIntersections(ray);
        Intersection intersection = IntersectionCollection.hit(intersections);
        if (intersection == null) {
            return new Color(0, 0, 0);
        }
        Computation computation = new Computation(intersection, ray);
        return shadeHit(computation);
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
