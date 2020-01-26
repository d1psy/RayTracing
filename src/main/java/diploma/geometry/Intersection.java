package diploma.geometry;

public class Intersection implements Comparable<Intersection> {

    private float time;
    private GeometryObject geometryObject;

    public Intersection(float time, GeometryObject geometryObject) {
        this.time = time;
        this.geometryObject = geometryObject;
    }

    public float getTime() {
        return time;
    }

    public GeometryObject getGeometryObject() {
        return geometryObject;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setGeometryObject(GeometryObject geometryObject) {
        this.geometryObject = geometryObject;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Intersection) {
            return getTime() == ((Intersection) obj).getTime()
                    && getGeometryObject() == ((Intersection) obj).getGeometryObject();
        }
        return false;
    }

    @Override
    public int compareTo(Intersection inter) {
        return Float.compare(getTime(), inter.getTime());
    }
}
