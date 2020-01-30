package diploma.geometry;

import diploma.ray.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wall extends GeometryObject {

    public Wall(int id) {
        super(id);
    }

    @Override
    public List<Intersection> getIntersection(Ray ray) {
        Ray ray2 = ray.transform(getTransformation().inverse());
        if(Math.abs(ray2.getDirection().getX()) < Math.ulp(1.0)) {
            return Collections.emptyList();
        } else {
            double t = (-ray2.getOrigin().getX())/ray2.getDirection().getX();
            List<Intersection> intersections = new ArrayList<>();
            intersections.add(new Intersection(t, this));
            return intersections;
        }
    }

    @Override
    public Tuple normalAt(Tuple point) {
        return new Vector(1, 0, 0);
    }

    @Override
    public Tuple localNormalAt(Tuple localPoint) {
        return null;
    }
}