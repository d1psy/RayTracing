package diploma.geometry;

import diploma.Creator;
import diploma.material.Material;
import diploma.ray.Ray;

import java.util.List;

public abstract class GeometryObject {

    private int id;
    private Point position;
    private Matrix transformation;
    private Material material;

    public GeometryObject(int id) {
        this.id = id;
        position = new Point(0.0, 0.0, 0.0);
        transformation = Creator.createIdentityMatrix();
        material = new Material(new Color(1.0, 1.0, 1.0));
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public abstract List<Intersection> getIntersection(Ray ray);

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Tuple normalAt(Tuple point) {
        Tuple localPoint = getTransformation().inverse().multiplyByTuple(point);
        Tuple localNormal = localNormalAt(localPoint);
        Tuple worldNormal = getTransformation().inverse().transpose().multiplyByTuple(localNormal);
        worldNormal.setW(0);
        return worldNormal.normalize();
    }

    public abstract Tuple localNormalAt(Tuple localPoint);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GeometryObject) {
            return ((GeometryObject) obj).getId() == id;
        }
        return false;
    }
}
