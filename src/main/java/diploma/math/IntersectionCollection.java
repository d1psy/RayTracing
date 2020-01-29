package diploma.math;

import diploma.geometry.Intersection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectionCollection {

    public static Intersection hit(List<Intersection> intersections) {
        List<Intersection> hitList = new ArrayList<>(intersections);
        Collections.sort(hitList);
        for (Intersection temp : hitList) {
            if (temp.getTime() >= 0) {
                return temp;
            }
        }
        return null;
    }
}
