package diploma.geometry;

import diploma.ray.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plane extends GeometryObject {

    public Plane(int id) {
        super(id);
    }

    @Override
    public List<Intersection> getIntersection(Ray ray) {
        Ray ray2 = ray.transform(getTransformation().inverse());
        if(Math.abs(ray2.getDirection().getY()) < Math.ulp(1.0)) {
            return Collections.emptyList();
        } else {
            double t = (-ray2.getOrigin().getY())/ray2.getDirection().getY();
            List<Intersection> intersections = new ArrayList<>();
            intersections.add(new Intersection(t, this));
            return intersections;
        }
    }

    @Override
    public Tuple normalAt(Tuple point) {
        return new Vector(0, 1, 0);
    }

    @Override
    public Tuple localNormalAt(Tuple localPoint) {
        return null;
    }
}
